package com.sda.weather.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionWeatherDto {     // todo we want to inform the user about the weather forecast (temp, humidity, wind)

    private Long id;
    private String name;
    private String country;
    private String region;
    private String lat;
    private String lon;

}
