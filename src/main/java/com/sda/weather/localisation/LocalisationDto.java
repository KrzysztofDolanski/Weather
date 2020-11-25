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
    private String city;
    @NotEmpty
    private String country;
    @Min(-90)
    @Max(90)
    private Float lat;
    @Min(-90)
    @Max(90)
    private Float lon;
    private String region;
}
