package com.sda.weather.localisation;


import com.sda.weather.exeptions.CityOrCountryBlankException;
import com.sda.weather.exeptions.NoCityOrCountryException;
import com.sda.weather.exeptions.NotFoundLocalisationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocalisationService {

    final LocalisationRepository localisationRepository;

    public Localisation createLocalisation(LocalisationDefinition localisationDefinition) {
        String countryName = localisationDefinition.getCountryName();
        String cityName = localisationDefinition.getCityName();
        String region = localisationDefinition.getRegion();

        if (cityName.isEmpty() || countryName.isEmpty()) {
            throw new NoCityOrCountryException("City and Country should not be empty");
        }

        if (cityName.isBlank() || countryName.isBlank() || region.isBlank()) {
            throw new CityOrCountryBlankException();
        }

        Localisation localisation = new Localisation();
        localisation.setCityName(cityName);
        localisation.setCountryName(countryName);
        localisation.setLatitude(localisationDefinition.getLatitude());
        localisation.setLongitude(localisationDefinition.getLongitude());
        localisation.setRegion(localisationDefinition.getRegion());

        return localisationRepository.save(localisation);
    }

    Localisation getLocalisationById(Long id) {
        return localisationRepository.findById(id)
                .orElseThrow(() -> new NotFoundLocalisationException("Nie znaleziono " + id));
    }


    public List<Localisation> getAllLocalisations() {
        return localisationRepository.findAll();
    }
}
