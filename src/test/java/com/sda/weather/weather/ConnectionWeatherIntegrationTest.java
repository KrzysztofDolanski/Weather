package com.sda.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@SpringBootTest
@AutoConfigureMockMvc
class ConnectionWeatherIntegrationTest {


    @Autowired
    MockMvc mockMvc;
    @Mock
    ConnectionWeatherSaveService connectionWeatherSaveService;
    @Mock
    ConnectionWeatherService connectionWeatherService;
    @Mock
    ConnectionWeatherRepository connectionWeatherRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void tryToGetWeatherFromApi() {
        //given
        String city = "Gdansk";
        ConnectionWeatherService connectionWeatherService = new ConnectionWeatherService();

        //when
        ConnectionWeather weatherInCity = connectionWeatherService.getForecast(city);

        //then
        assertThat(weatherInCity.getTemp_max()).isBetween(0d, 400d);
    }


    @Test
    void tryToConnectWithDatabase() {
        connectionWeatherRepository.deleteAll();
        List<ConnectionWeather> all = connectionWeatherRepository.findAll();

        assertThat(all.size()).isEqualTo(0);
    }

    @Test
    void tryToSaveWeatherLocationInDatabase() throws Exception {
        //given

        connectionWeatherRepository.deleteAll();

        String city = "Warsaw";

        MockHttpServletRequestBuilder request = get("/weather/" + city);

        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();

        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(connectionWeatherRepository.getOne(0l)).isNotNull();

    }

}
