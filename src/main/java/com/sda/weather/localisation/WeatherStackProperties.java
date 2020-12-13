package com.sda.weather.localisation;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "com.sda.weather.weather-stack")
public class WeatherStackProperties {

    private String token;
}
