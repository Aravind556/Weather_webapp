package com.example.Weather_forecast.ExceptionHandling;


public class CityNotFound extends RuntimeException {

    public CityNotFound(String city) {
        super("City not found");
    }

    public CityNotFound(String message, Throwable cause) {
        super(message, cause);
    }


    
}
