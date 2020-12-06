package com.sda.weather.forecast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationFetchService;
import com.sda.weather.localisation.LocalisationService;
import javassist.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForecastServiceTest {


    @InjectMocks
    LocalisationFetchService localisationFetchService;

    @Spy
    RestTemplate restTemplate;

    @Spy
    ObjectMapper objectMapper;

    @Mock
    ForecastService forecastService;

    @Test
    void getForecast() {

        String city = "Sopot";
        Localisation localisation = new Localisation();
        localisation.setCity(city);



        when(localisationFetchService.getLocalisation(1l).getCity().equals(anyString())).thenReturn(true);


        Forecast forecast = null;
        try {
            forecast = forecastService.getForecast("Gdańsk");
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(forecast);

    }
}
