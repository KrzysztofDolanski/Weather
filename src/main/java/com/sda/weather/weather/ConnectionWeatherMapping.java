package com.sda.weather.weather;

import org.springframework.stereotype.Component;

@Component
public class ConnectionWeatherMapping {


    public ConnectionWeather mapToConnectionWeather(ConnectionWeatherDto connectionWeatherDto){
        return ConnectionWeather.builder()
                .id(connectionWeatherDto.getId())
                .location(connectionWeatherDto.getLocation())
                .weather_descriptions(connectionWeatherDto.getWeather_descriptions())
                .build();
    }
}
