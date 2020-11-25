package com.sda.weather.weather;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.management.DescriptorKey;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "com.sda.weather.weather.weather")
public class APIConfiguration {

    String uri;
    String token;
    String query;

}
