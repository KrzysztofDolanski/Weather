package com.sda.weather.forecast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ForecastMappingTest {


    @Autowired
    LocalisationRepository localisationRepository;

    @Spy
    RestTemplate restTemplate;

    @Spy
    ObjectMapper objectMapper;

    @Mock
    ForecastService forecastService;





    @BeforeEach
    void setUp() {

    }


    @Test
    void mapToForecastDto() {
        //given
        Localisation localisation = new Localisation();
        localisation.setCity("Warsaw");
        localisation.setCountry("Poland");
        localisation.setLon(45f);
        localisation.setLat(20f);

        Forecast forecast = new Forecast();
        forecast.setLocalisation(localisation);
        forecast.setTemperature(12.0);
        forecast.setHumidity(0.1);
        forecast.setPressure(12.0);
        forecast.setWindDagre(12.3);
        forecast.setWindSpeed(50.0);

        ForecastMapping forecastMapping = new ForecastMapping();
        ForecastDto forecastDto = forecastMapping.mapToForecastDto(forecast);
        //when
        Double temperature = forecastDto.getTemperature();

        //then
        assertThat(temperature).isEqualTo(12.0);
        assertThat(forecastDto.getHumidity()).isEqualTo(forecast.getHumidity());
    }

    @Test
    void mapToForecast() {
    }
}
