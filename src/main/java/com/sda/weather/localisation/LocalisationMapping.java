package com.sda.weather.localisation;

import org.springframework.stereotype.Component;


@Component
public class LocalisationMapping {

    public LocalisationDefinition mapToLocalisationDefinition(LocalisationDto localisationDto) {
        return LocalisationDefinition.builder()
                .city(localisationDto.getCity())
                .country(localisationDto.getCountry())
                .lat(localisationDto.getLat())
                .lon(localisationDto.getLon())
                .region(localisationDto.getRegion())
                .build();
    }

    public LocalisationDto mapToLocalisationDto(Localisation createdLocalisation) {
        return LocalisationDto.builder()
                .id(createdLocalisation.getId())
                .city(createdLocalisation.getCity())
                .country(createdLocalisation.getCountry())
                .lat(createdLocalisation.getLat())
                .lon(createdLocalisation.getLon())
                .region(createdLocalisation.getRegion().orElse(null))
                .build();
    }
}
