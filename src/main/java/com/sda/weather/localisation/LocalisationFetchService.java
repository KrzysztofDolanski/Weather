package com.sda.weather.localisation;

import com.sda.weather.weather.ConnectionWeather;
import com.sda.weather.weather.ConnectionWeatherMapping;
import com.sda.weather.weather.ConnectionWeatherRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LocalisationFetchService {
    private static final String API_2_URL = "http://api.weatherstack.com/current?access_key=";
    private static final String TOKEN_2 = "35159824095457c4e2741bb6604b0ce3";
    private static final String QUERY = "&query=";

    RestTemplate restTemplate = new RestTemplate();

    private final LocalisationRepository localisationRepository;

    List<Localisation> getAllLocalisations() {
        return localisationRepository.findAll();
    }


    Localisation getLocalisation(Long id) {
        return localisationRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Cant localize localisation no" + id));
    }


    Localisation getLocalisationFromApi(String city) {

        String body = restTemplate.getForEntity(API_2_URL + TOKEN_2 + QUERY + city, String.class).getBody();


        JSONObject jsonObject = new JSONObject(body);
        JSONObject location = jsonObject.getJSONObject("location");

        System.err.println(location.toString());

        String city1 = location.getString("name");
        String country = location.getString("country");
        String region = location.getString("region");
        float lat = location.getFloat("lat");
        float lon = location.getFloat("lon");

        Localisation localisationFromApi = new Localisation();

        localisationFromApi.setCity(city1);
        localisationFromApi.setCountry(country);
        localisationFromApi.setRegion(region);
        localisationFromApi.setLat(lat);
        localisationFromApi.setLon(lon);

        return localisationFromApi;
    }
}
