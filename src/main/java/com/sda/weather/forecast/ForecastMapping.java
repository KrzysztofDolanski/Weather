package com.sda.weather.forecast;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
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
                .build();
    }

    public Forecast mapToForecast(ForecastOpenWeatherResponse forecastOpenWeatherResponse, int period) {

        return Forecast.builder()
                .temperature(forecastOpenWeatherResponse.getList().get(period).getMain().getTemp())
                .pressure(forecastOpenWeatherResponse.getList().get(period).getMain().getPressure())
                .humidity(forecastOpenWeatherResponse.getList().get(period).getMain().getHumidity())
                .windSpeed(forecastOpenWeatherResponse.getList().get(period).getWind().getSpeed())
                .windDagre(forecastOpenWeatherResponse.getList().get(period).getWind().getDeg())
                .date(forecastOpenWeatherResponse.getList().get(period).getDate())
                .build();
    }
}


