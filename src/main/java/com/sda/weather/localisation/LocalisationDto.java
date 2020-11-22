package com.sda.weather.localisation;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalisationDto {
    private Long id;
    private String cityName;
    private String countryName;
    private String latitude;
    private String longitude;
    private String region;
}
