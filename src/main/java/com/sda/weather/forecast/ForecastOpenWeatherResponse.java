package com.sda.weather.forecast;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ForecastOpenWeatherResponse {

    private CityResponse city;
    private List<SingleForecast> list;

    @Data
    public static class SingleForecast {
        Long dt;
        MainForecast main;
        List<Weather> weather;
        Clouds clouds;
        Wind wind;
        Double visibility;
        Double pop;
        Sys sys;
        @JsonProperty("dt_txt")
        String date;

        @Data
        public static class MainForecast {

            private Double temp;
            private Double feels_like;
            private Double temp_min;
            private Double temp_max;
            private Double pressure;
            private Double sea_level;
            private Double grnd_level;
            private Double humidity;
            private Double temp_kf;
        }

        @Data
        public static class Weather {
            private Long id;
            private String main;
            private String description;
            private String icon;
        }

        @Data
        public static class Clouds {
            private String all;
        }

        @Data
        public static class Wind {
            private Double speed;
            private Double deg;
        }

    }

    @Data
    public static class Sys{
        private String pop;
    }

    @Data
    static class CityResponse {
        private long id;
        private String name;
        private Coord coord;


        @Data
        public static class Coord {
            private double lat;
            private double lon;
        }
    }
}
