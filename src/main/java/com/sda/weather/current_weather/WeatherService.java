package com.sda.weather.current_weather;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.sda.weather.localisation.Localisation;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

@Component
public class WeatherService {


    private static final String API_URL = "api.openweathermap.org/data/2.5/forecast?q=London&appid=65bf43aa8dc4a2f7dc96da824bbc8205";

    RestTemplate restTemplate = new RestTemplate();

    void getConnection() {




//        ResponseEntity<Spring> forEntity = restTemplate.getForEntity(API_URL, Spring.class);
//        ResponseEntity<String> responseEntity = restTemplate.getForEntity(API_URL, String.class);

//        forEntity.getBody().getValue();
//
//        if (forObject.getStatusCode().is4xxClientError()){
//            throw new RuntimeException();
        }
    }



