package com.example.Weather_forecast.Model.external;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Forecast(
    @JsonProperty("forecastday")
    List<ForecastDay> forecastdays
) {
    
}
