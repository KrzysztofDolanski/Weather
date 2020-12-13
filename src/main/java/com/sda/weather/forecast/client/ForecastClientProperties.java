package com.sda.weather.forecast.client;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.sda.weather.open-weather")
public class ForecastClientProperties {

    private String token;
}
