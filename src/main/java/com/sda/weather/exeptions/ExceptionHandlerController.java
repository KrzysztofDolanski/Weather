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
}
