package com.sda.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sda.weather.localisation.LocalisationController;
import com.sda.weather.localisation.LocalisationRepository;
import com.sda.weather.localisation.LocalisationService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;

@Service
@RequiredArgsConstructor
public class ConnectionWeatherService {

    RestTemplate restTemplate = new RestTemplate();


    ConnectionWeatherMapping connectionWeatherMapping;
    ConnectionWeatherRepository connectionWeatherRepository;



    private static final String API_2_URL = "http://api.weatherstack.com/current?access_key=";
    private static final String TOKEN_2 = "35159824095457c4e2741bb6604b0ce3";
    private static final String QUERY = "&query=";


    private static final String API_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private static final String CONNECT_TO = "&appid=";
    private static final String TOKEN = "65bf43aa8dc4a2f7dc96da824bbc8205";


    public ConnectionWeather getEntity(String city){

        String body = restTemplate.getForEntity(API_2_URL + TOKEN_2 + QUERY + city, String.class).getBody();

        System.err.println(body);

        JSONObject jsonObject = new JSONObject(body);

        JSONObject location = jsonObject.getJSONObject("location");
        String name = location.getString("name");
        String country = location.getString("country");
        String region = location.getString("region");
        String lat = location.getString("lat");
        String lon = location.getString("lon");

        ConnectionWeather connectionWeather = ConnectionWeather.builder()
                .id(1l)
                .name(name)
                .lat(lat)
                .lon(lon)
                .country(country)
                .region(region)
                .build();

        return connectionWeather;
    }


    public void saveInDatabase(ConnectionWeather connectionWeather){
        connectionWeatherRepository.save(connectionWeather);
    }
}
