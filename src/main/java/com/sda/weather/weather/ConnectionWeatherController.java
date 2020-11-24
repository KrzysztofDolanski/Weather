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

    @PostMapping("/weather/{city}")     // todo we want to get a forecast (not create)
    ResponseEntity<ConnectionWeatherDto> getWeatherInCity(@PathVariable String city) {   // todo localization and period of the forecast
        ConnectionWeather entity = connectionWeatherService.getEntity(city);    // todo rename to eg. getForecast
        ConnectionWeatherDto connectionWeatherDto = connectionWeatherMapping.mapToConnectionWeatherDto(entity);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(connectionWeatherDto);
    }
}
