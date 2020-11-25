package com.sda.weather.weather;

import org.springframework.stereotype.Component;

import java.text.DecimalFormat;

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

    DecimalFormat df = new DecimalFormat("#.##");

    ConnectionWeatherDto mapToConnectionWeatherDtoInCelsius(ConnectionWeather connectionWeather) {
       TemperatureCalculator temperatureCalculator = new TemperatureCalculator();
        return ConnectionWeatherDto.builder()
                .temp(temperatureCalculator.fromKelwinToCelsius(connectionWeather.getTemp()))
                .feels_like(temperatureCalculator.fromKelwinToCelsius(connectionWeather.getFeels_like()))
                .temp_min(temperatureCalculator.fromKelwinToCelsius(connectionWeather.getTemp_min()))
                .temp_max(temperatureCalculator.fromKelwinToCelsius(connectionWeather.getTemp()))
                .pressure(connectionWeather.getPressure())
                .sea_level(connectionWeather.getSea_level())
                .grnd_level(connectionWeather.getGrnd_level())
                .humidity(connectionWeather.getHumidity())
                .temp_kf(connectionWeather.getTemp_kf())
                .build();
    }

}
