package com.sda.weather.localisation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalisationDefinition {

    private String cityName;
    private String countryName;
    private String latitude;
    private String longitude;
    private String region;
}
