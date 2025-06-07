package com.example.Weather_forecast.Model.external;

import com.fasterxml.jackson.annotation.JsonProperty;

public record Location(
    @JsonProperty("name") String name,
    @JsonProperty("country") String country, 
    @JsonProperty("localtime") String localtime) {}
