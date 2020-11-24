package com.sda.weather.localisation;


import com.sda.weather.exeptions.*;
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
        String latitude = localisationDefinition.getLatitude();
        String longitude = localisationDefinition.getLongitude();

        if (cityName.isEmpty() || countryName.isEmpty()) {
            throw new NoCityOrCountryException("City and Country should not be empty");
        }

        if (cityName.isBlank() || countryName.isBlank() || region.isBlank()) {
            throw new CityOrCountryBlankException();
        }

        if (Double.parseDouble(latitude)>90 ||Double.parseDouble(latitude)<-90){
            throw new LatitudeLimitValueException();
        }

        if (Double.parseDouble(longitude)>180 || Double.parseDouble(longitude)<-180){
            throw new LongitudeLimitValueException();
        }

        Localisation localisation = new Localisation();
        localisation.setCityName(cityName);
        localisation.setCountryName(countryName);
        localisation.setLatitude(latitude);
        localisation.setLongitude(longitude);
        localisation.setRegion(region);

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
