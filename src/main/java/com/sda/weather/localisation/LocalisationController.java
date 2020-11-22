package com.sda.weather.localisation;

//import com.sda.weather.weather.ConnectionService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class LocalisationController {

    final LocalisationService localisationService;
    final LocalisationMapping localisationMapping;


    @PostMapping("/localise")
    ResponseEntity<LocalisationDto> createLocalisation(@RequestBody LocalisationDto localisationDto) {
        LocalisationDefinition localisationDefinition = localisationMapping.mapToLocalisationDefinition(localisationDto);
        Localisation createdLocalisation = localisationService.createLocalisation(localisationDefinition);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localisationMapping.mapToLocalisationDto(createdLocalisation));
    }


    @GetMapping("/localise/{id}")
    LocalisationDto getLocalisationById(Long id) throws NotFoundException {
        Localisation localisationById = localisationService.getLocalisationById(id);
        return localisationMapping.mapToLocalisationDto(localisationById);
    }


    @GetMapping("/localise")
    List<Localisation> getLocalisations() {
        return localisationService.getAllLocalisations();
    }


}

