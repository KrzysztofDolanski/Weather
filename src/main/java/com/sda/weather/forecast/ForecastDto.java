package com.sda.weather.forecast;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ForecastDto {


    Long id;
    Double temperature;
    Double pressure;
    Double humidity;
    Double windDagre;
    Double windSpeed;


}
