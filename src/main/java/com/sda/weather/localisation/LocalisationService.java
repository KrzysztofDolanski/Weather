package com.sda.weather.localisation;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalisationService {

    final LocalisationRepository localisationRepository;


    public Localisation createLocalisation(String cityName, String countryName, String latitude, String longitude, String region) {
        if (cityName.isEmpty() || countryName.isEmpty()) {
            throw new NoCityorCountryExeption();
        }
        Localisation localisation = new Localisation();
        localisation.setCityName(cityName);
        localisation.setCountryName(countryName);
        localisation.setLatitude(latitude);
        localisation.setLongitude(longitude);
        localisation.setRegion(region);
        return localisationRepository.save(localisation);
    }



}
