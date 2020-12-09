package com.sda.weather.forecast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.exeptions.ForecastNotFountException;
import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ForecastService {

    private final LocalisationFetchService localisationFetchService;
    RestTemplate restTemplate = new RestTemplate();


    Date date = new Date();

    private final ObjectMapper objectMapper;
    private final OpenWeatherProperties openWeatherProperties;

    public Forecast getForecast(Long id, String period) throws JsonProcessingException {
        Localisation localisation = localisationFetchService.getLocalisation(id);

        String city = localisation.getCity();

        long time = date.getTime();
        System.out.println(time);

        String uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", city)
                .queryParam("appid", openWeatherProperties.getToken())
                .build().toUriString();

        ResponseEntity<String> forEntity = restTemplate.getForEntity(uri, String.class);
        String body = forEntity.getBody();

        System.err.println(body);

        ForecastOpenWeatherResponse forecastOpenWeatherResponse = objectMapper.readValue(body, ForecastOpenWeatherResponse.class);
        List<ForecastOpenWeatherResponse.SingleForecast> list = forecastOpenWeatherResponse.getList();

        //todo must change to period
        String str = "2020-12-10 12:00:00";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);

        ForecastOpenWeatherResponse.SingleForecast singleForecast = list
                .stream().filter(f -> LocalDateTime.parse(f.getDate(),formatter)
                .equals(dateTime))
                .findFirst()
                .orElseThrow(() -> new ForecastNotFountException("Cant find forecast for id " + id));

        Forecast forecastFromApi2 = Forecast.builder()
                .temperature(singleForecast.getMain().getTemp())
                .pressure(singleForecast.getMain().getPressure())
                .humidity(singleForecast.getMain().getHumidity())
                .windSpeed(singleForecast.getWind().getSpeed())
                .windDagre(singleForecast.getWind().getDeg())
                .date(singleForecast.getDate())
                .build();

                return forecastFromApi2;
        }
}

