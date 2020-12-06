package com.sda.weather.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ConnectionWeatherSaveService {

    private final ConnectionWeatherRepository connectionWeatherRepository;
    private final ConnectionWeatherService connectionWeatherService;

    public void saveInDataBaseConnectionWeather(ConnectionWeather connectionWeather){
        connectionWeatherRepository.save(connectionWeather);
    }
}
