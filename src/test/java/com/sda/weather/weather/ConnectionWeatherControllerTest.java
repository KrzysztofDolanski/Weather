package com.sda.weather.weather;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

//@AutoConfigureMockMvc
@SpringBootTest
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
    void tryToSaveWeatherLocationInDatabase() {
        //given
        ConnectionWeather london = connectionWeatherService.getForecast("London");

        ConnectionWeather gdansk = connectionWeatherService.getForecast("Gdansk");


        //when
        connectionWeatherService.saveInDatabase(london);
        connectionWeatherService.saveInDatabase(gdansk);     // todo
        // this is a unit test, so connectionWeatherRepository doesn't have a provided implementation
        // you can mock the behavior of the connectionWeatherRepository or you can write an integration tests (@SpringBootTest)
        // the integration test will launch the Spring Application, which will provide the implementation for the connectionWeatherRepository

        List<ConnectionWeather> all = connectionWeatherRepository.findAll();


        //then
        assertThat(all.size()).isEqualTo(2);


    }

}
