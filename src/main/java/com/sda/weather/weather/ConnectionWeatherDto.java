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

    private Double temp;
    private Double feels_like;
    private Double temp_min;
    private Double temp_max;
    private Double pressure;
    private Double sea_level;
    private Double grnd_level;
    private Double humidity;
    private Double temp_kf;

}
