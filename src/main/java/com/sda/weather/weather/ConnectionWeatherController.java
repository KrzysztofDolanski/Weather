package com.sda.weather.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConnectionWeatherController {

    final ConnectionWeatherMapping connectionWeatherMapping;
    final ConnectionWeatherService connectionWeatherService;

    @GetMapping("/weather/{city}")
    ResponseEntity<ConnectionWeatherDto> getWeatherInCity(@PathVariable String city) {   // todo localization and period of the forecast
        ConnectionWeather entity = connectionWeatherService.getForecast(city);
        ConnectionWeatherDto connectionWeatherDto = connectionWeatherMapping.mapToConnectionWeatherDto(entity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(connectionWeatherDto);
    }

    @GetMapping("/weatherCelsius/{city}")
    ResponseEntity<ConnectionWeatherDto> getWeatherInCelsiusFromCity(@PathVariable String city) {
        ConnectionWeather entity = connectionWeatherService.getForecast(city);
        ConnectionWeatherDto connectionWeatherDto = connectionWeatherMapping.mapToConnectionWeatherDtoInCelsius(entity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(connectionWeatherDto);
    }
}
