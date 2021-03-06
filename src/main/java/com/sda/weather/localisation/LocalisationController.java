package com.sda.weather.localisation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@Validated
public class LocalisationController {

    final LocalisationService localisationService;
    final LocalisationMapping localisationMapping;
    final LocalisationFetchService localisationFetchService;

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PostMapping("/localise")
    public
    ResponseEntity<LocalisationDto>
    createLocalisation(@RequestBody @Valid LocalisationDto localisationDto) {
        LocalisationDefinition localisationDefinition = localisationMapping.mapToLocalisationDefinition(localisationDto);
        Localisation createdLocalisation = localisationService.createLocalisation(localisationDefinition);

        return
                ResponseEntity
                .status(HttpStatus.CREATED)
                .body(localisationMapping.mapToLocalisationDto(createdLocalisation));

    }

    @GetMapping("/localise/{id}")
    LocalisationDto getLocalisationById(@PathVariable @Min(1) Long id) {
        Localisation localisationById = localisationFetchService.getLocalisation(id);
        return localisationMapping.mapToLocalisationDto(localisationById);
    }


    @GetMapping("/localise")
    List<LocalisationDto> getLocalisations() {
        return localisationFetchService.getAllLocalisations().stream()
                .map(localisationMapping::mapToLocalisationDto)
                .collect(Collectors.toList());
    }

    // todo to remove
    @GetMapping("/localiseCity/{city}")
    LocalisationDto getLocalisationValues(@PathVariable String city){
        return localisationMapping.mapToLocalisationDto(localisationFetchService.getLocalisationFromApi(city));
    }

}

