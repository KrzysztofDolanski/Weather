package com.sda.weather.forecast;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
public class ForecastController {

    private final ForecastService forecastService;
    private final ForecastMapping forecastMapping;


    @GetMapping("/localise/{id}/forecast")
    ForecastDto getForecast(@PathVariable Long id, @RequestParam(required = false, defaultValue = "1") @Min(1) @Max(5) Integer period) {
        Forecast forecast = forecastService.getForecast(id, period);
        return forecastMapping.mapToForecastDto(forecast);
    }
}



