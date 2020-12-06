package com.sda.weather.forecast;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationFetchService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ForecastService {

    private final LocalisationFetchService localisationFetchService;
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ObjectMapper objectMapper; // todo make final

    public Forecast getForecast(Long id, String period) {
        Localisation localisation = localisationFetchService.getLocalisation(id);

        String city = localisation.getCity();

        String uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", city)
                .queryParam("appid", "65bf43aa8dc4a2f7dc96da824bbc8205")
                .build().toUriString();

        ResponseEntity<String> forEntity = restTemplate.getForEntity(uri, String.class);
        String body = forEntity.getBody();

        try {
            // todo exception is thrown here
            ForecastOpenWeatherResponse forecastOpenWeatherResponse = objectMapper.readValue(body, ForecastOpenWeatherResponse.class);

            List<ForecastOpenWeatherResponse.SingleForecast> list = forecastOpenWeatherResponse.getList();


            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getDate().equals(period)) {
                    return Forecast.builder()
                            .temperature(list.get(i).getMain().getTemp())
                            .pressure(list.get(i).getMain().getPressure())
                            .humidity(list.get(i).getMain().getHumidity())
                            .windDagre(list.get(i).getWind().getDeg())
                            .windSpeed(list.get(i).getWind().getSpeed())
                            .date(list.get(i).getDate())
                            .build();
                }
            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
