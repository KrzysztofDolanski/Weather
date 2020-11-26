package com.sda.weather.exeptions;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException() {
        super("you try to find city that dont exists");
    }
}
