package com.sda.weather.forecast;

import com.sda.weather.localisation.Localisation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ForecastMappingTest {

    @Mock
    WindDirectionMapping windDirectionMapping;
    @InjectMocks
    ForecastMapping forecastMapping;

    @Test
    void mapToForecastDto() {
        //given
        when(windDirectionMapping.mapWindDirection(any())).thenReturn("N");
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

        //when
        ForecastDto forecastDto = forecastMapping.mapToForecastDto(forecast);

        //then
        assertThat(forecastDto.getTemperature()).isEqualTo(12.2);
        assertThat(forecastDto.getHumidity()).isEqualTo(0.1);
    }
}
