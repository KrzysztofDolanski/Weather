package com.sda.weather.forecast;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;
    private final ForecastMapping forecastMapping;
    private final ForecastSaveToDataBaseService forecastSaveToDataBaseService;

    @GetMapping("/localise/{id}/forecast")
    ForecastDto getForecast(@PathVariable Long id, @RequestParam(required = false) LocalDate forecastDate) throws JsonProcessingException {
        Forecast forecast = forecastService.getForecast(id, forecastDate);
        forecastSaveToDataBaseService.saveForecastToDataBase(id, forecastDate);
        return forecastMapping.mapToForecastDto(forecast);
    }




}



