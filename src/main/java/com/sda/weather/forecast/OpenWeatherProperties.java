package com.sda.weather.forecast;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "com.sda.weather.open-weather")
public class OpenWeatherProperties {


    private String token;
}
