package com.example.Weather_forecast.Model;


import java.util.List;

public record WeeklyForecast(
    String Name,
    String country,
    List<DailyForecast> forecast
) { }
