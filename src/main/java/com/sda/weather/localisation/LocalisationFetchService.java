package com.sda.weather.localisation;

import com.sda.weather.exeptions.LocalisationInDataBaseNotFoundException;
import com.sda.weather.weather.ConnectionWeather;
import com.sda.weather.weather.ConnectionWeatherMapping;
import com.sda.weather.weather.ConnectionWeatherRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Data
@Component
@RequiredArgsConstructor
public class LocalisationFetchService {

    RestTemplate restTemplate = new RestTemplate();

    private final LocalisationRepository localisationRepository;
    private final WeatherStackProperties weatherStackProperties;

    List<Localisation> getAllLocalisations() {
        return localisationRepository.findAll();
    }

    public Localisation getLocalisation(Long id) {
        return localisationRepository.findById(id)
                .orElseThrow(() -> new LocalisationInDataBaseNotFoundException("Cant localize localisation no" + id)); // todo use your own exception
    }

    // todo to remove
    Localisation getLocalisationFromApi(String city) {

        String uri = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host("api.weatherstack.com/current")
                .queryParam("access_key", weatherStackProperties.getToken())
                .queryParam("query", city)
                .build().toUriString();

        ResponseEntity<String> forEntity = restTemplate.getForEntity(uri, String.class);
        String body = forEntity.getBody();

        JSONObject jsonObject = new JSONObject(body);
        JSONObject location = jsonObject.getJSONObject("location");

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

        localisationRepository.save(localisationFromApi);

        return localisationFromApi;
    }
}
