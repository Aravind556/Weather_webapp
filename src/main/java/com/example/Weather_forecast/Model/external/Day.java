package com.example.Weather_forecast.Model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Day(
    @JsonProperty("maxtemp_c")
    double maxtemp,
    @JsonProperty("mintemp_c")
    double mintemp,
    @JsonProperty("avgtemp_c")
    double avgtemp,
    @JsonProperty("avghumidity")
    int humidity,
    @JsonProperty("totalprecip_mm")
    double precipitation,
    @JsonProperty("daily_chance_of_rain")
    int chanceOfRain,
    Condition condition
) {
    
}
