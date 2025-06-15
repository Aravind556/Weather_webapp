package com.example.Weather_forecast.service;

import com.example.Weather_forecast.Model.WeatherData;
import com.example.Weather_forecast.Model.WeeklyForecast;

import reactor.core.publisher.Mono;

public interface WeatherService {
    Mono<WeatherData> getcurrentweather(String city);
    Mono<WeeklyForecast> getweeklyforecast(String city);  
}
