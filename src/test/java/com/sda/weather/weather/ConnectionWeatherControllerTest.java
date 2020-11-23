package com.sda.weather.weather;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

class ConnectionWeatherControllerTest {


    @Test
    void tryToGetWeatherFromApi(){
        //given
        String city = "Gdansk";
        ConnectionWeatherService connectionWeatherService = new ConnectionWeatherService();

        //when
        ConnectionWeatherController connectionWeatherController = new ConnectionWeatherController(connectionWeatherService);
        ResponseEntity<ConnectionWeather> weatherInCity = connectionWeatherController.getWeatherInCity(city);

        //then
        String current = weatherInCity.getBody().getCurrent();
        Long id = weatherInCity.getBody().getId();
        String location = weatherInCity.getBody().getLocation();
        String weather_descriptions = weatherInCity.getBody().getWeather_descriptions();

        System.out.println(current);

        assertThat(weatherInCity.getStatusCode()).isEqualTo(HttpStatus.OK.value());

    }

}
