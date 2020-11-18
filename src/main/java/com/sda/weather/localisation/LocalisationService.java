package com.sda.weather.localisation;


import com.sda.weather.exeptions.NoCityOrCountryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LocalisationService {

    final LocalisationRepository localisationRepository;


    public Localisation createLocalisation(String cityName, String countryName, String latitude, String longitude, String region) {
        if (cityName.isEmpty() || countryName.isEmpty()) {
            throw new NoCityOrCountryException("City and Country should not be empty");
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
