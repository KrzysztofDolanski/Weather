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

        Double temp = 0.0;
        Double feels_like = 0.0;
        Double temp_min = 0.0;
        Double temp_max = 0.0;
        Double pressure = 0.0;
        Double sea_level = 0.0;
        Double grnd_level = 0.0;
        Double humidity = 0.0;
        Double temp_kf = 0.0;

        for (Object o : list) {
            JSONObject jsonObject2 = new JSONObject(o.toString());
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
        }

//        Object forecast4 = list.get(3);

////        JSONObject jsonObject1 = new JSONObject(forecast4.toString());
//        System.err.println(jsonObject1);
//        System.out.println(jsonObject1.length());

//        JSONObject mainForecast = jsonObject1.getJSONObject("main");

//        temp = mainForecast.getDouble("temp");
//        feels_like = mainForecast.getDouble("feels_like");
//        temp_min = mainForecast.getDouble("temp_min");
//        temp_max = mainForecast.getDouble("temp_max");
//        pressure = mainForecast.getDouble("pressure");
//        sea_level = mainForecast.getDouble("sea_level");
//        grnd_level = mainForecast.getDouble("grnd_level");
//        humidity = mainForecast.getDouble("humidity");
//        temp_kf = mainForecast.getDouble("temp_kf");

        ConnectionWeather connectionWeather = ConnectionWeather.builder()
                .temp(temp/list.length())
                .feels_like(feels_like/list.length())
                .temp_min(temp_min/list.length())
                .temp_max(temp_max/list.length())
                .pressure(pressure/list.length())
                .sea_level(sea_level/list.length())
                .grnd_level(grnd_level/list.length())
                .humidity(humidity/list.length())
                .temp_kf(temp_kf/list.length())
                .build();

        return connectionWeather;
    }


    public void saveInDatabase(ConnectionWeather connectionWeather) {
        connectionWeatherRepository.save(connectionWeather);
    }
}
