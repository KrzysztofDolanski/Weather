package com.sda.weather.forecast;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.forecast.client.ForecastClientProperties;
import com.sda.weather.localisation.Localisation;
import com.sda.weather.localisation.LocalisationRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ForecastMappingTest {


    @Autowired
    LocalisationRepository localisationRepository;

    @Spy
    RestTemplate restTemplate;


    @Autowired
    Forecast forecast;

    @Autowired
    ForecastMapping forecastMapping;

    @Spy
    ObjectMapper objectMapper;

    @Mock
    ForecastRepository forecastRepository;


    @BeforeEach
    void setUp() {

    }


    @Test
    void mapToForecastDto() {
        //given
        forecastRepository.deleteAll();
        Localisation localisation = new Localisation();
        localisation.setCity("Warsaw");
        localisation.setCountry("Poland");
        localisation.setLon(45f);
        localisation.setLat(20f);



        Forecast forecast = Forecast.builder()
                .localisation(localisation)
                .temperature(12.2)
                .humidity(0.1)
                .pressure(12.2)
                .windDagre(22.1)
                .windSpeed(50.0)
                .createdDate(Instant.now())
                .build();

        System.out.println(forecast);

        ForecastDto forecastDto = forecastMapping.mapToForecastDto(forecast);
        //when
        Double temperature = forecastDto.getTemperature();

        //then
        assertThat(temperature).isEqualTo(12.2);
        assertThat(forecastDto.getHumidity()).isEqualTo(forecast.getHumidity());
    }

    @Test
    void mapToForecast() {
    }
}
