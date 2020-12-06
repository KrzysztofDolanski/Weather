package com.sda.weather.forecast;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;
    private final ForecastMapping forecastMapping;

    @GetMapping("localise/{id}/forecast")
    ForecastDto getForecast(@PathVariable Long id, @RequestParam(required = false) String period) {
        Forecast forecast = forecastService.getForecast(id, period);
        return forecastMapping.mapToForecastDto(forecast);
    }
}



