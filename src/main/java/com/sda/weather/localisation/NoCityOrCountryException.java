package com.sda.weather.localisation;

public class NoCityOrCountryException extends RuntimeException {


    public NoCityOrCountryException(String message) {
        super(message);
    }
}
