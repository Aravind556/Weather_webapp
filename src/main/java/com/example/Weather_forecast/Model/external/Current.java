package com.example.Weather_forecast.Model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Current(
    @JsonProperty("temp_c") double temp,
    @JsonProperty("feelslike_c") double feelsLike,
    int humidity,
    @JsonProperty("wind_kph") double windSpeed,
    Condition condition
    ) {  
}
