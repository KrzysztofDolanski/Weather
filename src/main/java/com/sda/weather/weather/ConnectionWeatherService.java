package com.sda.weather.weather;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConnectionWeatherService {

    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper objectMapper = new ObjectMapper();
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
        try {
            ConnectionWeatherDto connectionWeatherDto = objectMapper.readValue(body, ConnectionWeatherDto.class);
            ConnectionWeather connectionWeather = connectionWeatherMapping.mapToConnectionWeather(connectionWeatherDto);

            connectionWeatherRepository.save(connectionWeather);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return new ConnectionWeather();
    }
}
