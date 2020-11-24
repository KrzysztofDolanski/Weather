package com.sda.weather.weather;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ConnectionWeatherController {

    final ConnectionWeatherMapping connectionWeatherMapping;
    final ConnectionWeatherService connectionWeatherService;

    @PostMapping("/weather/{city}")
    public ResponseEntity<ConnectionWeatherDto> getWeatherInCity(@PathVariable String city) {
        ConnectionWeather entity = connectionWeatherService.getEntity(city);
        ConnectionWeatherDto connectionWeatherDto = connectionWeatherMapping.mapToConnectionWeatherDto(entity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(connectionWeatherDto);
    }

}
