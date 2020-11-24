package com.sda.weather.localisation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.ws.rs.NotFoundException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LocalisationFetchService {

    private final LocalisationRepository localisationRepository;

    List<Localisation> getAllLocalisations(){
      return localisationRepository.findAll();
    }


    Localisation getLocalisation(Long id){
        return localisationRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Cant localize localisation no" + id));
    }
}
