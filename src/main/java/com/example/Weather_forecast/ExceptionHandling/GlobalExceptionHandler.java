package com.example.Weather_forecast.ExceptionHandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CityNotFound.class)
    public Mono<ResponseEntity<String>> handleCityNotFoundException(CityNotFound ex) {
        return Mono.just(ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body("City not found"));
    }
    
    // Added exception handler WebClientResponseException
    @ExceptionHandler(WebClientResponseException.class)
    public Mono<ResponseEntity<String>> handleWebClientResponseException(WebClientResponseException ex) {
        // 400 for bad city name
        if (ex.getStatusCode().value() == 400) {
            return Mono.just(ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("City not found"));
        }
        
        // for other errors, shouldnt be any
        return Mono.just(ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .body("Error fetching weather data"));
    }
    
    // IDK WHY THIS DIDNT WORK IN THE CONTROLLER BUT MAKING IT AS AN EXCEPTION HERE IS WORKING FOR IDK WHY
    @ExceptionHandler(IllegalArgumentException.class)
    public Mono<ResponseEntity<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        return Mono.just(ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body("Please enter a city name"));
    }
}
