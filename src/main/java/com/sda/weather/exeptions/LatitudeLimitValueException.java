package com.sda.weather.exeptions;

public class LatitudeLimitValueException extends RuntimeException {

    public LatitudeLimitValueException() {
        super("Latitude value should be between -90 and 90 included");
    }
}
