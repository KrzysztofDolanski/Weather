package com.sda.weather.localisation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Builder
@Data
public class LocalisationDefinition {

    private final String cityName;
    private final String countryName;
    private final int latitude;
    private final int longitude;
    private final String region;
}
