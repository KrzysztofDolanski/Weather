package com.sda.weather.weather;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class ConnectionWeatherControllerTest {


    @Mock
    ConnectionWeatherRepository connectionWeatherRepository;

    @InjectMocks
    ConnectionWeatherService connectionWeatherService;


    @Test
    void tryToGetWeatherFromApi() {
        //given
        String city = "Gdansk";
        ConnectionWeatherService connectionWeatherService = new ConnectionWeatherService();

        //when
        ConnectionWeather weatherInCity = connectionWeatherService.getEntity(city);

        //then
        assertThat(weatherInCity.getName()).isEqualTo(city);
    }

    @Test
    void tryToSaveWeatherLocationInDatabase() {
        //given
        ConnectionWeather build = ConnectionWeather.builder()
                .country("Polska")
                .lat("12")
                .lon("123")
                .name("Gdansk")
                .region("pomorskie")
                .build();

        //when
        connectionWeatherService.saveInDatabase(build);     // todo
                                                            // this is a unit test, so connectionWeatherRepository doesn't have a provided implementation
                                                            // you can mock the behavior of the connectionWeatherRepository or you can write an integration tests (@SpringBootTest)
                                                            // the integration test will launch the Spring Application, which will provide the implementation for the connectionWeatherRepository

        List<ConnectionWeather> all = connectionWeatherRepository.findAll();


        //then
        assertThat(all.size()).isEqualTo(1);



    }

}
