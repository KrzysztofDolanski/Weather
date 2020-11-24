package com.sda.weather.localisation;


import com.sda.weather.exeptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LocalisationService {

    final LocalisationRepository localisationRepository;

    public Localisation createLocalisation(LocalisationDefinition localisationDefinition) {
        String countryName = localisationDefinition.getCountryName();
        String cityName = localisationDefinition.getCityName();
        String region = localisationDefinition.getRegion();
        int latitude = localisationDefinition.getLatitude();
        int longitude = localisationDefinition.getLongitude();

        if (cityName.isEmpty() || countryName.isEmpty()) {
            throw new NoCityOrCountryException("City and Country should not be empty");
        }

        if (cityName.isBlank() || countryName.isBlank() || region.isBlank()) {
            throw new CityOrCountryBlankException();
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
