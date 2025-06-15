package com.example.Weather_forecast.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Weather_forecast.Model.WeatherData;
import com.example.Weather_forecast.Model.WeeklyForecast;
import com.example.Weather_forecast.service.WeatherService;

import ch.qos.logback.classic.Logger;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(WeatherController.class);
    

    @GetMapping("/current")
    public Mono<ResponseEntity<WeatherData>> currentweather(@RequestParam(required = false) String city) {
        logger.info("Fetching current weather for city: " + city);
        return weatherService.getcurrentweather(city)
                .map(ResponseEntity::ok)
                .onErrorResume(e -> {
                    logger.error("Error fetching weather data for city: " + city, e);
                    return Mono.just(ResponseEntity.status(500).body(null));
                });
    }

    @GetMapping("/forecast")
    public Mono<ResponseEntity<WeeklyForecast>> WeeklyForecast(@RequestParam String city) {
        logger.info("Fetching weekly forecast");
        return weatherService.getweeklyforecast(city)
            .map(ResponseEntity::ok)
            .onErrorResume(e-> {
                logger.error("Error fetching the weekly forecast");
                return Mono.just(ResponseEntity.status(500).body(null));
            });
    }
    
    

    
}
