package com.sda.weather.exeptions;

public class CityOrCountryBlankException extends RuntimeException{

    public CityOrCountryBlankException() {
        super("City and Country should not be blank");
    }
}
