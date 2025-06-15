package com.example.Weather_forecast.Model;

//This file is for the each day weather forecast 

public record DailyForecast(
    String date,
    double maxTemperature,
    double minTemperature,
    double avgTemperature,
    int humidity,
    double precipitation,
    int chanceOfRain,
    String condition,
    String conditionIcon,
    String sunrise,
    String sunset
) {}
