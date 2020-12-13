package com.sda.weather.forecast;

import com.sda.weather.exeptions.ThisIsNotWindException;
import org.springframework.stereotype.Component;

@Component
public class WindDirectionMapping {

    private final static String[] DIRECTIONS = {"N", "NNE", "NE", "ENE", "E", "ESE", "SE", "SSE", "S", "SSW", "SW", "WSW", "W", "WNW", "NW", "NNW"};

    String mapWindDirection(Double windDirection) {
        if (windDirection > 360 || windDirection < 0) {
            throw new ThisIsNotWindException("That wind can't be mapped: " + windDirection);
        }

        double value = (windDirection / 22.5) + 0.5;
        return DIRECTIONS[(int) (value % 16)];
    }
}
