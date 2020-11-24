package com.sda.weather.localisation;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocalisationDto {
    private Long id;

    @NotEmpty
    private String cityName;
    @NotEmpty
    private String countryName;
    @Min(-90)
    @Max(90)
    private int latitude;
    @Min(-90)
    @Max(90)
    private int longitude;
    private String region;
}
