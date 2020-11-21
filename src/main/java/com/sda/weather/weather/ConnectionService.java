package com.sda.weather.weather;

import com.sda.weather.exeptions.NoWeatherFindException;
import com.sda.weather.localisation.LocalisationDto;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Component
public class ConnectionService {


    private static final String API_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
            private static final String CONNECT_TO = "&appid=";
            private static final String TOKEN = "65bf43aa8dc4a2f7dc96da824bbc8205";


    RestTemplate restTemplate = new RestTemplate();

    private List<LocalisationDto> dtoList = new ArrayList<>();
    private final String TO_GET_ONLY_NUMBERS_AND_MINUS = "[A-z]*[aA!$%^&*()_+|~=`{}\\[\\]:\";'<>?,\\/]";
    private String countryName = null;
    private String cityname = null;
    private String latitude = null;
    private String longitude = null;
    private String region = null;


    public List<LocalisationDto> getCityLocalisation(String city) {

        String forObject = restTemplate.getForObject(API_URL + city +CONNECT_TO +TOKEN, String.class);

        if (forObject == null) {
            throw new NoWeatherFindException("Brak połączenia");
        }

        String[] split = forObject.split(",");

        String countryNameDirty = split[split.length - 5];
        String longitudeDirty = split[split.length - 6];
        String latitudeDirty = split[split.length - 7];
        String cityNameDirty = split[split.length - 8];

        String[] countryArrayToGetCountryName = countryNameDirty.split(":");
        String countryNameToReplacement = countryArrayToGetCountryName[1];
        String replaceCountryName = countryNameToReplacement.replace('"', '}');
        countryName = replaceCountryName.replaceAll("}", "");

        String[] cityArrayToGetCityName = cityNameDirty.split(":");
        String cityNameToReplacement = cityArrayToGetCityName[1];
        String replaceCityNAme = cityNameToReplacement.replace('"', '}');
        cityname = replaceCityNAme.replaceAll("}", "");

        latitude = latitudeDirty.replaceAll(TO_GET_ONLY_NUMBERS_AND_MINUS, "");
        longitude = longitudeDirty.replaceAll(TO_GET_ONLY_NUMBERS_AND_MINUS, "");

        System.out.println(countryName);
        System.out.println(cityname);
        System.out.println(latitude);
        System.out.println(longitude);

        dtoList.add(new LocalisationDto(null, cityname, countryName, latitude, longitude, region));
        return dtoList;
    }

}



