package com.sda.weather.weather;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ConnectionWeatherServiceTest {

    @Test
    void getHttpStatusOK_whenConnectingToApiIsSuccessful() {
        //given
        String API_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
        String CONNECT_TO = "&appid=";
        String TOKEN = "65bf43aa8dc4a2f7dc96da824bbc8205";
        String city = "Gdańsk";
        RestTemplate restTemplate = new RestTemplate();
        HttpStatus statusCode = restTemplate.getForEntity(API_URL + city + CONNECT_TO + TOKEN, String.class).getStatusCode();

        //wheh
        //then
        assertThat(statusCode).isEqualTo(HttpStatus.OK);
    }

    @Test
    void checkIfListFromJsonIsNotEmpty() {
        //given
        String API_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
        String CONNECT_TO = "&appid=";
        String TOKEN = "65bf43aa8dc4a2f7dc96da824bbc8205";

        String city = "Gdańsk";

        RestTemplate restTemplate = new RestTemplate();

        String body = restTemplate.getForEntity(API_URL + city + CONNECT_TO + TOKEN, String.class).getBody();

        //when
        JSONObject jsonObject = new JSONObject(body);
        JSONArray list = jsonObject.getJSONArray("list");
        //then
        assertThat(list.get(0)).isNotNull();
    }

    @Test
    void checkIfMainFromFirstListIsNotEmpty() {
        //given
        String API_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
        String CONNECT_TO = "&appid=";
        String TOKEN = "65bf43aa8dc4a2f7dc96da824bbc8205";

        String city = "Gdańsk";

        RestTemplate restTemplate = new RestTemplate();

        String body = restTemplate.getForEntity(API_URL + city + CONNECT_TO + TOKEN, String.class).getBody();

        //when
        JSONObject jsonObject = new JSONObject(body);
        JSONArray list = jsonObject.getJSONArray("list");


        Double temp = 0.0;
        Double feels_like = 0.0;
        Double temp_min = 0.0;
        Double temp_max = 0.0;
        Double pressure = 0.0;
        Double sea_level = 0.0;
        Double grnd_level = 0.0;
        Double humidity = 0.0;
        Double temp_kf = 0.0;


            JSONObject jsonObject2 = new JSONObject(list.get(0).toString());
            JSONObject main = jsonObject2.getJSONObject("main");

            temp += main.getDouble("temp");
            feels_like += main.getDouble("feels_like");
            temp_min += main.getDouble("temp_min");
            temp_max += main.getDouble("temp_max");
            pressure += main.getDouble("pressure");
            sea_level += main.getDouble("sea_level");
            grnd_level += main.getDouble("grnd_level");
            humidity += main.getDouble("humidity");
            temp_kf += main.getDouble("temp_kf");

        //then
        assertThat(main).isNotNull();

    }

    @Test
    void checkIfTemperatureIsGreaterThanLowestTemperatureInHistory() {
        //given
        String API_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
        String CONNECT_TO = "&appid=";
        String TOKEN = "65bf43aa8dc4a2f7dc96da824bbc8205";

        String city = "Gdańsk";

        RestTemplate restTemplate = new RestTemplate();

        String body = restTemplate.getForEntity(API_URL + city + CONNECT_TO + TOKEN, String.class).getBody();

        //when
        JSONObject jsonObject = new JSONObject(body);
        JSONArray list = jsonObject.getJSONArray("list");


        Double temp = 0.0;
        Double feels_like = 0.0;
        Double temp_min = 0.0;
        Double temp_max = 0.0;
        Double pressure = 0.0;
        Double sea_level = 0.0;
        Double grnd_level = 0.0;
        Double humidity = 0.0;
        Double temp_kf = 0.0;


        JSONObject jsonObject2 = new JSONObject(list.get(0).toString());
        JSONObject main = jsonObject2.getJSONObject("main");

        temp += main.getDouble("temp");
        feels_like += main.getDouble("feels_like");
        temp_min += main.getDouble("temp_min");
        temp_max += main.getDouble("temp_max");
        pressure += main.getDouble("pressure");
        sea_level += main.getDouble("sea_level");
        grnd_level += main.getDouble("grnd_level");
        humidity += main.getDouble("humidity");
        temp_kf += main.getDouble("temp_kf");


        //then

        assertThat(temp).isGreaterThan(-98);
    }
}

