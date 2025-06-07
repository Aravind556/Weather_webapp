package com.example.Weather_forecast.service;

import java.net.URI;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.Weather_forecast.Model.WeatherData;


import ch.qos.logback.classic.Logger;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;

import com.example.Weather_forecast.Model.external.ApiResponse;

import reactor.core.publisher.Mono;


@Service
public class Weatherimpl implements WeatherService {

    private final WebClient webClient;

    public Logger logger = (Logger) LoggerFactory.getLogger(Weatherimpl.class);

    public Weatherimpl(@Qualifier("weatherwebClient")WebClient webClient) {
        this.webClient = webClient;
    }
    @Value("${weather.api.key}")
    private  String apikey;

    @Override
    public Mono<WeatherData> getcurrentweather(String city) { 
        // Check for null or empty city parameter
        if (city == null || city.trim().isEmpty()) {
            logger.error("City parameter cannot be null or empty");
            return Mono.error(new IllegalArgumentException("City parameter is required"));
        }
        
        return webClient
                .get()
                .uri(uriBuilder -> {
                    URI uri = uriBuilder
                        .path("/current.json")
                        .queryParam("q", city)
                        .queryParam("key", apikey)
                        .build();
                    logger.info("Making request to: {}", uri.toString().replace(apikey, "API_KEY_HIDDEN"));
                    return uri;
                })
                .retrieve()
                .bodyToMono(ApiResponse.class)
                .map(apiresponse -> {
                    logger.info("Received weather data for city: " + city);
                    return new WeatherData(
                        apiresponse.location().name(),
                        apiresponse.location().country(),
                        apiresponse.current().temp(),
                        apiresponse.current().feelsLike(),
                        apiresponse.current().humidity(),
                        apiresponse.current().windSpeed(),
                        apiresponse.current().condition().text(),
                        apiresponse.location().localtime()
                    );
                });
             
    }
}
