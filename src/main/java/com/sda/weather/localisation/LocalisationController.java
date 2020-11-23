package com.sda.weather.localisation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocalisationController {

    final LocalisationService localisationService;
    final LocalisationMapping localisationMapping;

    @PostMapping("/localise")
    public ResponseEntity<LocalisationDto> createLocalisation(@RequestBody LocalisationDto localisationDto) {
        LocalisationDefinition localisationDefinition = localisationMapping.mapToLocalisationDefinition(localisationDto);
        Localisation createdLocalisation = localisationService.createLocalisation(localisationDefinition);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localisationMapping.mapToLocalisationDto(createdLocalisation));
    }


    @GetMapping("/localise/{id}")
    LocalisationDto getLocalisationById(@PathVariable Long id) {
        Localisation localisationById = localisationService.getLocalisationById(id);
        return localisationMapping.mapToLocalisationDto(localisationById);
    }


    @GetMapping("/localise")
    List<Localisation> getLocalisations() {
        return localisationService.getAllLocalisations();
    }

}

