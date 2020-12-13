package com.sda.weather.exeptions;

import javax.ws.rs.InternalServerErrorException;

public class ThisIsNotWindException extends InternalServerErrorException {
    public ThisIsNotWindException(String s) {
    }
}
