package com.example.Weather_forecast.Model;


public record WeatherData (
    String city,
    String country,
    double temperature,
    double feels_like,
    int humidity,
    double wind_speed,
    String condition,
    String localtime
) {}