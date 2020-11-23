package com.sda.weather.weather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConnectionWeatherDto {

    private Long id;
    private String location;
    private String current;
    private String weather_descriptions;
}
