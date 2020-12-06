package com.sda.weather.weather;

import org.apache.commons.math3.util.Precision;
import org.springframework.stereotype.Component;

@Component
public class ConnectionWeatherMapping {

    ConnectionWeatherDto mapToConnectionWeatherDto(ConnectionWeather connectionWeather) {
        return ConnectionWeatherDto.builder()
                .temp(Precision.round(connectionWeather.getTemp(), 2))
                .feels_like(Precision.round(connectionWeather.getFeels_like(), 2))
                .temp_min(Precision.round(connectionWeather.getTemp_min(), 2))
                .temp_max(Precision.round(connectionWeather.getTemp(), 2))
                .pressure(Precision.round(connectionWeather.getPressure(), 2))
                .sea_level(Precision.round(connectionWeather.getSea_level(), 2))
                .grnd_level(Precision.round(connectionWeather.getGrnd_level(), 2))
                .humidity(Precision.round(connectionWeather.getHumidity(), 2))
                .temp_kf(Precision.round(connectionWeather.getTemp_kf(), 2))
                .build();
    }


    ConnectionWeatherDto mapToConnectionWeatherDtoInCelsius(ConnectionWeather connectionWeather) {
        TemperatureCalculator temperatureCalculator = new TemperatureCalculator();
        return ConnectionWeatherDto.builder()
                .temp(Precision.round(temperatureCalculator.fromKelwinToCelsius(connectionWeather.getTemp()), 2))
                .feels_like(Precision.round(temperatureCalculator.fromKelwinToCelsius(connectionWeather.getFeels_like()), 2))
                .temp_min(Precision.round(temperatureCalculator.fromKelwinToCelsius(connectionWeather.getTemp_min()), 2))
                .temp_max(Precision.round(temperatureCalculator.fromKelwinToCelsius(connectionWeather.getTemp()), 2))
                .pressure(Precision.round(connectionWeather.getPressure(), 2))
                .sea_level(Precision.round(connectionWeather.getSea_level(), 2))
                .grnd_level(Precision.round(connectionWeather.getGrnd_level(), 2))
                .humidity(Precision.round(connectionWeather.getHumidity(), 2))
                .temp_kf(Precision.round(connectionWeather.getTemp_kf(), 2))
                .build();
    }


    ConnectionWeather mapToConnectionWeather(ConnectionWeatherDto connectionWeatherDto) {
        return ConnectionWeather.builder()
                .temp(connectionWeatherDto.getTemp())
                .feels_like(connectionWeatherDto.getFeels_like())
                .temp_min(connectionWeatherDto.getTemp_min())
                .temp_max(connectionWeatherDto.getTemp_max())
                .pressure(connectionWeatherDto.getPressure())
                .sea_level(connectionWeatherDto.getSea_level())
                .grnd_level(connectionWeatherDto.getGrnd_level())
                .humidity(connectionWeatherDto.getHumidity())
                .temp_kf(connectionWeatherDto.getTemp_kf())
                .build();
    }

}
