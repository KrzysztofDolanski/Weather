package com.sda.weather.forecast;

import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationFetchService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.configuration.injection.MockInjection;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForecastServiceTest {


    @Mock
    LocalisationFetchService localisationFetchService;

    @InjectMocks
    ForecastService forecastService;

    @Test
    void getForecast() {

        Localisation localisation = localisationFetchService.getLocalisation(1l);

        forecastService.getForecast(1l,"4");
    }
}
