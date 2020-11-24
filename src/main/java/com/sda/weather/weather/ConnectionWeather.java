package com.sda.weather.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Entity
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
@AllArgsConstructor
public class ConnectionWeather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;
    private String lat;
    private String lon;
    private String country;
    private String region;


    public ConnectionWeather(String name, String lat, String lon, String country, String region) {
        this.name = name;
        this.lat = lat;
        this.lon = lon;
        this.country = country;
        this.region = region;
    }
}
