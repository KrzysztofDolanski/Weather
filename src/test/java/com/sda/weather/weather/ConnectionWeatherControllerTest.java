package com.sda.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.stereotype.Repository;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.mockito.Mockito.mock;
import static org.springframework.mock.http.server.reactive.MockServerHttpRequest.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class ConnectionWeatherControllerTest {


    @Autowired
    MockMvc mockMvc;

    @Mock
    ConnectionWeatherRepository connectionWeatherRepository;

    @InjectMocks
    ConnectionWeatherService connectionWeatherService;

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void tryToGetWeatherFromApi() {
        //given
        String city = "Gdansk";
        ConnectionWeatherService connectionWeatherService = new ConnectionWeatherService();

        //when
        ConnectionWeather weatherInCity = connectionWeatherService.getForecast(city);

        //then
        assertThat(weatherInCity.getTemp_max()).isBetween(0d,400d);
    }


    @Test
    void tryToConnectWithDatabase() {
        connectionWeatherRepository.deleteAll();
        List<ConnectionWeather> all = connectionWeatherRepository.findAll();

        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    void tryToSaveWeatherLocationInDatabase() throws JsonProcessingException {
        //given
        ConnectionWeather london = connectionWeatherService.getForecast("London");

        when(connectionWeatherRepository.save(any(ConnectionWeather.class))).thenReturn(london);
        //when

        Optional<ConnectionWeather> byId = connectionWeatherRepository.findById(london.getId());

        // todo
        // this is a unit test, so connectionWeatherRepository doesn't have a provided implementation
        // you can mock the behavior of the connectionWeatherRepository
        // the integration test will launch the Spring Application, which will provide the implementation for the connectionWeatherRepository


        //then
        assertThat(byId.get().getTemp()).isEqualTo(london.getTemp());

    }

}
