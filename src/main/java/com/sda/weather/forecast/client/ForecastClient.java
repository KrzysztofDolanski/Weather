package com.sda.weather.forecast.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.forecast.Forecast;
import com.sda.weather.forecast.ForecastMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ForecastClient {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final ForecastClientProperties forecastClientProperties;
    private final ForecastMapping forecastMapping;

    public Optional<Forecast> getForecast(String city, LocalDate period) {
        String uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.openweathermap.org/data/2.5/forecast")
                .queryParam("q", city)
                .queryParam("appid", forecastClientProperties.getToken())
                .build().toUriString();

        try {
            ResponseEntity<String> forEntity = restTemplate.getForEntity(uri, String.class);
            String body = forEntity.getBody();


            if (forEntity.getStatusCode().isError()) {
                return Optional.empty();
            }

            ForecastOpenWeatherResponse forecastOpenWeatherResponse = objectMapper.readValue(body, ForecastOpenWeatherResponse.class);

            LocalDateTime predictionDate = period.atTime(12, 00);

            return forecastOpenWeatherResponse.getSingleForecast().stream().filter(f -> mapToLocalDate(f.getDate()).isEqual(predictionDate))
                    .map(this::mapToForecast)
                    .findFirst();

        } catch (JsonProcessingException | RestClientException e) {
            return Optional.empty();
        }
    }

    public Forecast mapToForecast(ForecastOpenWeatherResponse.SingleForecast singleForecast) {

        LocalDateTime forecastDate = mapToLocalDate(singleForecast.getDate());
        Instant forecastDateInstant = forecastDate.atZone(ZoneId.systemDefault()).toInstant();

        return Forecast.builder()
                .temperature(singleForecast.getMain().getTemp())
                .pressure(singleForecast.getMain().getPressure())
                .humidity(singleForecast.getMain().getHumidity())
                .windSpeed(singleForecast.getWind().getSpeed())
                .windDagre(singleForecast.getWind().getDeg())
                .createdDate(forecastDateInstant)
                .build();
    }

    private LocalDateTime mapToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.parse(date, formatter);

    }

}
