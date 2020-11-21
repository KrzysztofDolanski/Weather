package com.sda.weather.localisation;

import com.sda.weather.weather.ConnectionService;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocalisationController {

    final LocalisationService localisationService;
    final ConnectionService connectionService;

    @PostMapping("/localise")
    ResponseEntity<LocalisationDto> createLocalisation(@RequestBody LocalisationDto localisationDto){
        LocalisationDefinition localisationDefinition = mapToLocalisationDefinition(localisationDto);
        Localisation createdLocalisation = localisationService.createLocalisation(localisationDefinition);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(mapToLocalisationDto(createdLocalisation));
    }

    private LocalisationDefinition mapToLocalisationDefinition(LocalisationDto localisationDto) {
        LocalisationDefinition localisationDefinition = new LocalisationDefinition();
        localisationDefinition.setCityName(localisationDto.getCityName());
        localisationDefinition.setCountryName(localisationDto.getCountryName());
        localisationDefinition.setLatitude(localisationDto.getLatitude());
        localisationDefinition.setLongitude(localisationDto.getLongitude());
        localisationDefinition.setRegion(localisationDto.getRegion());
        return localisationDefinition;
    }

    private LocalisationDto mapToLocalisationDto(Localisation createdLocalisation) {
        LocalisationDto localisationDto = new LocalisationDto();
        localisationDto.setId(createdLocalisation.getId());
        localisationDto.setCityName(createdLocalisation.getCityName());
        localisationDto.setCountryName(createdLocalisation.getCountryName());
        localisationDto.setLatitude(createdLocalisation.getLatitude());
        localisationDto.setLongitude(createdLocalisation.getLongitude());
        localisationDto.setRegion(createdLocalisation.getRegion());

        return localisationDto;
    }

    @GetMapping("/localise/{id}")
    LocalisationDto getLocalisationById(Long id) throws NotFoundException {
        Localisation localisationById = localisationService.getLocalisationById(id);
        return mapToLocalisationDto(localisationById);
    }


    @GetMapping("/localise")
    List<LocalisationDto> getLocasisations(){
        LocalisationDto localisationDto = new LocalisationDto();
        localisationDto.setCityName("Sopot");
        localisationDto.setCountryName("Polska");
        localisationDto.setLatitude("54.4447922");
        localisationDto.setLongitude("18.5684902");
        localisationDto.setRegion("pomorskie");
        return Collections.singletonList(localisationDto);
    }

    @GetMapping("/localise/{cityName}")
    void saveLocalisationInDatabase (String cityName){
        List<LocalisationDto> cityLocalisation = connectionService.getCityLocalisation(cityName);
        LocalisationDto localisationDto = cityLocalisation.get(0);
        localisationService.saveLocalisationInDatabase(localisationDto);
    }
}
