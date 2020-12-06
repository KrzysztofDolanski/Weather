package com.sda.weather.localisation;


import com.sda.weather.exeptions.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LocalisationService {

    final LocalisationRepository localisationRepository;

    public Localisation createLocalisation(LocalisationDefinition localisationDefinition) {
        String countryName = localisationDefinition.getCountry();
        String cityName = localisationDefinition.getCity();
        String region = localisationDefinition.getRegion();
        Float latitude = localisationDefinition.getLat();
        Float longitude = localisationDefinition.getLon();

        if (cityName.isEmpty() || countryName.isEmpty()) {
            throw new NoCityOrCountryException("City and Country should not be empty");
        }

        // todo region can be blank
        if (cityName.isBlank() || countryName.isBlank() || region.isBlank()) {
            throw new CityOrCountryBlankException();
        }

        Localisation localisation = new Localisation();
        localisation.setCity(cityName);
        localisation.setCountry(countryName);
        localisation.setLat(latitude);
        localisation.setLon(longitude);
        localisation.setRegion(region);

        return localisationRepository.save(localisation);
    }
}
