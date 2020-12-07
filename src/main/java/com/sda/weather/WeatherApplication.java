package com.sda.weather;

import com.sda.weather.forecast.OpenWeatherProperties;
import com.sda.weather.localisation.WeatherStackProperties;
import com.sda.weather.security.MvcConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties ({OpenWeatherProperties.class, WeatherStackProperties.class})
public class WeatherApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeatherApplication.class, args);
    }
}
