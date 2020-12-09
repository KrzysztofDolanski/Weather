package com.sda.weather.forecast;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class ForecastSaveToDataBaseService {

    private final ForecastRepository forecastRepository;
    private final ForecastService forecastService;

    void saveForecastToDataBase(Long id, LocalDate forecastDate) throws JsonProcessingException {
        forecastRepository.save(forecastService.getForecast(id, forecastDate));
    }
}
