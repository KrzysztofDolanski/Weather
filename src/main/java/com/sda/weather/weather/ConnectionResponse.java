package com.sda.weather.weather;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ConnectionResponse {

    @JsonProperty
    ConnectionWeatherDto connectionWeatherDto;

}
