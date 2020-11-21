package com.sda.weather.localisation;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class LocalisationDefinition {
    private String cityName;
    private String countryName;
    private String latitude;
    private String longitude;
    private String region;
}
