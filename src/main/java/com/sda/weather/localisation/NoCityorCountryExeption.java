package com.sda.weather.localisation;

public class NoCityorCountryExeption extends RuntimeException {


    public NoCityorCountryExeption() {
        super("City and Country should not be empty");
    }
}
