package com.sda.weather.localisation;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Component
public class LocalisationMapping {



    public LocalisationDefinition mapToLocalisationDefinition(LocalisationDto localisationDto) {
        return LocalisationDefinition.builder()
                .cityName(localisationDto.getCityName())
                .countryName(localisationDto.getCountryName())
                .latitude(localisationDto.getLatitude())
                .longitude(localisationDto.getLongitude())
                .region(localisationDto.getRegion())
                .build();

    }

    public LocalisationDto mapToLocalisationDto(Localisation createdLocalisation) {
        return LocalisationDto.builder()
                .id(createdLocalisation.getId())
                .cityName(createdLocalisation.getCityName())
                .countryName(createdLocalisation.getCountryName())
                .latitude(createdLocalisation.getLatitude())
                .longitude(createdLocalisation.getLongitude())
                .region(createdLocalisation.getRegion())
                .build();

    }
}
