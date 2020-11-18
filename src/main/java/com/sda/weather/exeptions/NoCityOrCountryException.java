package com.sda.weather.exeptions;

public class NoCityOrCountryException extends RuntimeException {


    public NoCityOrCountryException(String message) {
        super("SOMETHING GO WRONG: "+ message);
    }
}
