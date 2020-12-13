package com.sda.weather.localisation;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class LocalisationDefinition {

    private final String city;
    private final String country;
    private final Float lat;
    private final Float lon;
    private final String region;
}
