package com.sda.weather.weather;

import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@RequiredArgsConstructor
public class ConnectionWeatherService {

    private static final String API_2_URL = "http://api.weatherstack.com/current?access_key=";
    private static final String TOKEN_2 = "35159824095457c4e2741bb6604b0ce3";
    private static final String QUERY = "&query=";
    private static final String API_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private static final String CONNECT_TO = "&appid=";
    private static final String TOKEN = "65bf43aa8dc4a2f7dc96da824bbc8205";

    RestTemplate restTemplate = new RestTemplate();
    APIConfiguration apiConfiguration;
    ConnectionWeatherRepository connectionWeatherRepository;

    public ConnectionWeather getForecast(String city) {

        String body = restTemplate.getForEntity(API_URL + city + CONNECT_TO + TOKEN, String.class).getBody();

        JSONObject jsonObject = new JSONObject(body);
        JSONArray list = jsonObject.getJSONArray("list");
        Object forecast4 = list.get(3);

        JSONObject jsonObject1 = new JSONObject(forecast4.toString());
        System.err.println(jsonObject1);
        System.out.println(jsonObject1.length());

        JSONObject mainForecast = jsonObject1.getJSONObject("main");

        Double temp = mainForecast.getDouble("temp");
        Double feels_like = mainForecast.getDouble("feels_like");
        Double temp_min = mainForecast.getDouble("temp_min");
        Double temp_max = mainForecast.getDouble("temp_max");
        Double pressure = mainForecast.getDouble("pressure");
        Double sea_level = mainForecast.getDouble("sea_level");
        Double grnd_level = mainForecast.getDouble("grnd_level");
        Double humidity = mainForecast.getDouble("humidity");
        Double temp_kf = mainForecast.getDouble("temp_kf");

        ConnectionWeather connectionWeather = ConnectionWeather.builder()
                .temp(temp)
                .feels_like(feels_like)
                .temp_min(temp_min)
                .temp_max(temp_max)
                .pressure(pressure)
                .sea_level(sea_level)
                .grnd_level(grnd_level)
                .humidity(humidity)
                .temp_kf(temp_kf)
                .build();

        return connectionWeather;
    }


    public void saveInDatabase(ConnectionWeather connectionWeather) {
        connectionWeatherRepository.save(connectionWeather);
    }
}
