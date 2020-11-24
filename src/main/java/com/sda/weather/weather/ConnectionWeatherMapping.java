package com.sda.weather.weather;

import org.springframework.stereotype.Component;

@Component
public class ConnectionWeatherMapping {


    public ConnectionWeatherDto mapToConnectionWeatherDto(ConnectionWeather connectionWeather){
        return ConnectionWeatherDto.builder()
                .id(connectionWeather.getId())
                .country(connectionWeather.getCountry())
                .name(connectionWeather.getName())
                .region(connectionWeather.getRegion())
                .lat(connectionWeather.getLat())
                .lon(connectionWeather.getLon())
                .build();
    }
}
