package com.sda.weather.weather;

import org.springframework.stereotype.Component;

@Component
public class ConnectionWeatherMapping {

    ConnectionWeatherDto mapToConnectionWeatherDto(ConnectionWeather connectionWeather) {
        return ConnectionWeatherDto.builder()
                .temp(connectionWeather.getTemp())
                .feels_like(connectionWeather.getFeels_like())
                .temp_min(connectionWeather.getTemp_min())
                .temp_max(connectionWeather.getTemp())
                .pressure(connectionWeather.getPressure())
                .sea_level(connectionWeather.getSea_level())
                .grnd_level(connectionWeather.getGrnd_level())
                .humidity(connectionWeather.getHumidity())
                .temp_kf(connectionWeather.getTemp_kf())
                .build();
    }
}
