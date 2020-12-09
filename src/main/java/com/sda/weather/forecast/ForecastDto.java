package com.sda.weather.forecast;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForecastDto {


    private Double temperature;
    private Double pressure;
    private Double humidity;
   private String windDirection;
    private Double windSpeed;
    private String date;



}
