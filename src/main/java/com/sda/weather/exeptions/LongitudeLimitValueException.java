package com.sda.weather.exeptions;

public class LongitudeLimitValueException extends RuntimeException {

    public LongitudeLimitValueException() {
        super("Longitude value should be between -180 and 180 included");
    }
}
