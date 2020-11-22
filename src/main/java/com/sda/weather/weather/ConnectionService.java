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


    private static final String API_2_URL = "http://api.weatherstack.com/current?access_key=";
    private static final String TOKEN_2 = "35159824095457c4e2741bb6604b0ce3";
    private static final String QUERY = "&query=";


    private static final String API_URL = "http://api.openweathermap.org/data/2.5/forecast?q=";
    private static final String CONNECT_TO = "&appid=";
    private static final String TOKEN = "65bf43aa8dc4a2f7dc96da824bbc8205";


    RestTemplate restTemplate = new RestTemplate();

    private List<LocalisationDto> dtoList = new ArrayList<>();
    private final String TO_GET_ONLY_NUMBERS_AND_MINUS = "[A-z]*[aA!$%^&*()_+|~=`{}\\[\\]:\";'<>?,\\/]";
    private Long id = null;
    private String countryName = null;
    private String cityname = null;
    private String latitude = null;
    private String longitude = null;
    private String region = null;

    private String temperature;
    private String pressure;
    private String humidity;
    private String widnDirection;
    private String windSpeed;



    public List<LocalisationDto> getCityLocalisationFromOpenWeatherMap(String city) {
        String forObject = restTemplate.getForObject(API_URL + city + CONNECT_TO + TOKEN, String.class);

        if (forObject == null) {
            throw new NoWeatherFindException("Brak połączenia");
        }

        String[] split = forObject.split(",");

        for (String s : split) {
            System.out.println(s);
        }

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

        dtoList.add(new LocalisationDto(id, cityname, countryName, latitude, longitude, region));
        return dtoList;
    }



    public List<LocalisationDto> getCityLocalisationFromWeatherStack(String city) {
        String forObject1 = restTemplate.getForObject(API_2_URL + TOKEN_2 + QUERY + city, String.class);

        if (forObject1 == null) {
            throw new NoWeatherFindException("Brak połączenia");
        }

        String[] split = forObject1.split(",");

        for (String s : split) {
            System.out.println(s);
        }

        String countryNameDirty = split[5];
        String longitudeDirty = split[8];
        String latitudeDirty = split[7];
        String cityNameDirty = split[4];
        String regionDirty = split[6];


        //optional values of weather


        //todo make this 5 values useful

        String temperature = split[14];
        String pressure = split[21];
        String humidity = split[23];
        String windDirection = split[20];
        String windSpeed = split[18];

        System.err.println(countryNameDirty);
        System.err.println(latitudeDirty);
        System.err.println(latitudeDirty);
        System.err.println(cityNameDirty);
        System.err.println(regionDirty);


        System.err.println("OPTIONAL VALUES");
        System.err.println(temperature);
        System.err.println(pressure);
        System.err.println(humidity);
        System.err.println(windDirection);
        System.err.println(windSpeed);

        String[] countryArrayToGetCountryName = countryNameDirty.split(":");
        String countryNameToReplacement = countryArrayToGetCountryName[1];
        String replaceCountryName = countryNameToReplacement.replace('"', '}');
        countryName = replaceCountryName.replaceAll("}", "");

        String[] cityArrayToGetCityName = cityNameDirty.split(":");
        String cityNameToReplacement = cityArrayToGetCityName[2];
        String replaceCityNAme = cityNameToReplacement.replace('"', '}');
        cityname = replaceCityNAme.replaceAll("}", "");

        String[] regionArrayToGetregion = regionDirty.split(":");
        String regionToReplacement = regionArrayToGetregion[1];
        String replaceRegion = regionToReplacement.replace('"', '}');
        region = replaceRegion.replaceAll("}", "");

        latitude = latitudeDirty.replaceAll(TO_GET_ONLY_NUMBERS_AND_MINUS, "");
        longitude = longitudeDirty.replaceAll(TO_GET_ONLY_NUMBERS_AND_MINUS, "");

        System.out.println(countryName);
        System.out.println(cityname);
        System.out.println(latitude);
        System.out.println(longitude);
        System.out.println(region);

        dtoList.add(new LocalisationDto(id, cityname, countryName, latitude, longitude, region));
        return dtoList;
    }

}



