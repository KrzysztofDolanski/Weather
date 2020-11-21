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
    void isCityNameFromApiIsValid() {

        String city = "Gdańsk";

        ConnectionService connectionService = new ConnectionService();
        List<LocalisationDto> cityLocalisation = connectionService.getCityLocalisation(city);

        assertThat(cityLocalisation.get(0).getCityName()).isEqualTo("Gdańsk");

    }
    }
