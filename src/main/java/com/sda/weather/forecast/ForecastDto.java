package com.sda.weather.forecast;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
public class ForecastDto {


    private final Double temperature;
    private final Double pressure;
    private final Double humidity;
    private final String windDirection;
    private final Double windSpeed;
    private final String date;


}
