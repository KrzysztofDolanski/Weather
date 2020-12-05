package com.sda.weather.weather;

import com.sda.weather.APIConfiguration;
import com.sda.weather.exeptions.CityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ConnectionWeatherService {

    private static final String API_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private static final String CONNECT_TO = "&appid=";
    private static final String TOKEN = "65bf43aa8dc4a2f7dc96da824bbc8205";

    RestTemplate restTemplate = new RestTemplate();

    ConnectionWeatherRepository connectionWeatherRepository;

    public ConnectionWeather getForecast(String city) {
        String body = null;
        // todo check if Localization exists in our database
        try {
            // todo check status code of a response
            body = restTemplate.getForEntity(API_URL + city + CONNECT_TO + TOKEN, String.class).getBody();
        } catch (CityNotFoundException e) {
            System.err.println(e);
        }
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

        ConnectionWeather connectionWeather = ConnectionWeather.builder()
                .temp(temp / list.length())
                .feels_like(feels_like / list.length())
                .temp_min(temp_min / list.length())
                .temp_max(temp_max / list.length())
                .pressure(pressure / list.length())
                .sea_level(sea_level / list.length())
                .grnd_level(grnd_level / list.length())
                .humidity(humidity / list.length())
                .temp_kf(temp_kf / list.length())
                .build();
        // todo save ConnectionWeather to the database (relation with Localization!)
        return connectionWeather;
    }

    public void saveInDatabase(ConnectionWeather connectionWeather) {   // todo remove if unnecessary
        connectionWeatherRepository.save(connectionWeather);
    }
}
