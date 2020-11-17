package com.sda.weather.localisation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class LocalisationController {


    final LocalisationService localisationService;



    @PostMapping("/localise")
    ResponseEntity<LocalisationDto> createLocalisation(@RequestBody LocalisationDto localisationDto){
        String cityName = localisationDto.getCityName();
        String countryName = localisationDto.getCountryName();
        String latitude = localisationDto.getLatitude();
        String longitude = localisationDto.getLongitude();
        String region = localisationDto.getRegion();
        Localisation createdLocalisation = localisationService.createLocalisation(cityName, countryName, latitude, longitude, region);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapToLocalisationDto(createdLocalisation));
    }

    private LocalisationDto mapToLocalisationDto(Localisation createdLocalisation) {
        LocalisationDto localisationDto = new LocalisationDto();
        localisationDto.setCityName(createdLocalisation.getCityName());
        localisationDto.setCountryName(createdLocalisation.getCountryName());
        localisationDto.setLatitude(createdLocalisation.getLatitude());
        localisationDto.setLongitude(createdLocalisation.getLongitude());
        localisationDto.setRegion(createdLocalisation.getRegion());
        return localisationDto;
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
}
