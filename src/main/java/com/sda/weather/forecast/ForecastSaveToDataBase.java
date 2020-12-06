package com.sda.weather.forecast;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ForecastSaveToDataBase {

    private final ForecastRepository forecastRepository;
    private final ForecastService forecastService;

    void saveForecastToDataBase(Long id, String period) throws JsonProcessingException {
        forecastRepository.save(forecastService.getForecast(id, period));
    }
}
