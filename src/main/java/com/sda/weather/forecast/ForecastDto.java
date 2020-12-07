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


    Double temperature;
    Double pressure;
    Double humidity;
    Double windDagre;
    Double windSpeed;



}
