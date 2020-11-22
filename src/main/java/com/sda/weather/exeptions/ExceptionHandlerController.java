package com.sda.weather.exeptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerController {

    @ExceptionHandler(NoCityOrCountryException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void noCityOrCountryHandler(NoCityOrCountryException e){
      log.error(e.getMessage());
    }

    @ExceptionHandler(CityOrCountryBlankException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    void cityOrCountryShouldNotBeBlank(CityOrCountryBlankException e){
        log.error(e.getMessage());
    }

    @ExceptionHandler(NoWeatherFindException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void noWeatherFindInWebService(NoWeatherFindException e){
        log.error(e.getMessage());
    }

    @ExceptionHandler(NotFoundLocalisationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void noWeatherFindInWebService(NotFoundLocalisationException e){
        log.error(e.getMessage());
    }

}
