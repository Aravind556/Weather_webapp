package com.example.Weather_forecast.Model.external;

public record ApiResponse(
    Current current,
    Location location) {
}
