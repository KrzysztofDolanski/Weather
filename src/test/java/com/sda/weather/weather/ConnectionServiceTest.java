package com.sda.weather.weather;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.localisation.LocalisationDto;
import com.sda.weather.localisation.LocalisationRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
@ExtendWith(MockitoExtension.class)
class ConnectionServiceTest {


    @Autowired
    MockMvc mockMvc;

    @Autowired
    LocalisationRepository localisationRepository;

    ObjectMapper objectMapper = new ObjectMapper();
    LocalisationDto localisationDto = new LocalisationDto();


    @Test
    void isCityNameIsValid_FromOpenWeatherMapApi() {

        //when
        ConnectionService connectionService = new ConnectionService();

        //given
        String city = "Gdańsk";
        List<LocalisationDto> cityLocalisation = connectionService.getCityLocalisationFromOpenWeatherMap(city);

        //then
        assertThat(cityLocalisation.get(0).getCityName()).isEqualTo("Gdańsk");
    }

    @Test
    void isCityNameIsValid_FromWeatherStackApi() {

        //when
        ConnectionService connectionService = new ConnectionService();

        //given
        String city = "New York";
        List<LocalisationDto> cityLocalisation = connectionService.getCityLocalisationFromWeatherStack(city);

        //then
        assertThat(cityLocalisation.get(0).getCityName()).isEqualTo("New York");
    }
}
