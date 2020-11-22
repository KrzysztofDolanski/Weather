package com.sda.weather.localisation;


import com.sda.weather.exeptions.CityOrCountryBlankException;
import com.sda.weather.exeptions.NoCityOrCountryException;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LocalisationService {

    final LocalisationRepository localisationRepository;

    public Localisation createLocalisation(LocalisationDefinition localisationDefinition) {
        String countryName = localisationDefinition.getCountryName();
        String cityName = localisationDefinition.getCityName();

        if (cityName.isEmpty() || countryName.isEmpty()) {
            throw new NoCityOrCountryException("City and Country should not be empty");
        }

        // todo use isBlank

//          Pattern compiledPattern = Pattern.compile("Marcin");
//          Matcher matcher = compiledPattern.matcher("Nazywam sie Marcin Pietraszek");
//          System.out.println(matcher.find());
//  todo remove
//        String REGEX_CHEQUE_IF_STRING_NOT_EMPTY = "((?!\\s*$)+[A-ńA-śA-źA-ż])\\w+";
//        if (!cityName.equals(REGEX_CHEQUE_IF_STRING_NOT_EMPTY) || !countryName.equals(REGEX_CHEQUE_IF_STRING_NOT_EMPTY)) {
//            throw new CityOrCountryBlankException();
//        }

        Localisation localisation = new Localisation();
        localisation.setCityName(cityName);
        localisation.setCountryName(countryName);
        localisation.setLatitude(localisationDefinition.getLatitude());
        localisation.setLongitude(localisation.getLongitude());         // todo use localisationDefinition.getLongitude()
        localisation.setRegion(localisationDefinition.getRegion());     // todo check if isBlank, then don't save it

        return localisationRepository.save(localisation);
    }

    Localisation getLocalisationById(Long id) {
        return localisationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nie znaleziono " + id));   // todo create your own exception
    }

    public void saveLocalisationInDatabase(LocalisationDto localisationDto) {   // todo remove
        Localisation localisation = new Localisation();
        localisation.setCountryName(localisationDto.getCountryName());
        localisation.setCityName(localisationDto.getCityName());
        localisation.setLatitude(localisationDto.getLatitude());
        localisation.setLongitude(localisationDto.getLongitude());
        localisation.setRegion(localisationDto.getRegion());
        localisationRepository.save(localisation);
    }

    public List<Localisation> getAllLocalisations() {
        return localisationRepository.findAll();
    }
}
