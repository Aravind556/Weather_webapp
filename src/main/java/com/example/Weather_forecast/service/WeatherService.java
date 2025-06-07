package com.example.Weather_forecast.service;

import com.example.Weather_forecast.Model.WeatherData;

import reactor.core.publisher.Mono;

public interface WeatherService {
    Mono<WeatherData> getcurrentweather(String city);
    
}
