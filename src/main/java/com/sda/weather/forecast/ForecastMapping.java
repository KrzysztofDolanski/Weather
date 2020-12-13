package com.sda.weather.forecast;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForecastMapping {

    private final WindDirectionMapping windDirectionMapping;

    public ForecastDto mapToForecastDto(Forecast forecast) {
        return ForecastDto.builder()
                .temperature(forecast.getTemperature())
                .pressure(forecast.getPressure())
                .humidity(forecast.getHumidity())
                .windSpeed(forecast.getWindSpeed())
                .windDirection(windDirectionMapping.mapWindDirection(forecast.getWindDagre()))
                .date(forecast.getCreatedDate().toString())
                .build();
    }

}


