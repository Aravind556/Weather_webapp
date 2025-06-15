package com.example.Weather_forecast.Model.external;

public record ForecastResponse(

    Location location,
    Current current,
    Forecast forecast
) {}
