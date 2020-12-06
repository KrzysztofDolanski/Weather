package com.sda.weather.forecast;

import javassist.NotFoundException;
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

    @GetMapping("localise/forecast/{city}")
    ForecastDto getForecast(@PathVariable String city) {

        Forecast forecast = null;
        try {
            forecast = forecastService.getForecast(city);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        return forecastMapping.mapToForecastDto(forecast);
    }

}



