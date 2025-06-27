package com.example.Weather_forecast.service;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import com.example.Weather_forecast.ExceptionHandling.CityNotFound;
import com.example.Weather_forecast.Model.DailyForecast;
import com.example.Weather_forecast.Model.WeatherData;
import com.example.Weather_forecast.Model.WeeklyForecast;
import com.example.Weather_forecast.Model.external.ApiResponse;
import com.example.Weather_forecast.Model.external.ForecastResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import ch.qos.logback.classic.Logger;
import reactor.core.publisher.Mono;


@Service
public class Weatherimpl implements WeatherService {

    private final WebClient webClient;

    public Logger logger = (Logger) LoggerFactory.getLogger(Weatherimpl.class);

    public Weatherimpl(@Qualifier("DailyweaterClient")WebClient webClient) {
        this.webClient = webClient;
    }
    @Value("${weather.api.key}")
    private  String apikey;

@Override
public Mono<WeatherData> getcurrentweather(String city) {
    if (city == null || city.trim().isEmpty()) {
        return Mono.error(new IllegalArgumentException("City parameter is required"));
    }
    
    return webClient
        .get()
        .uri(uriBuilder -> uriBuilder
            .path("/current.json")
            .queryParam("q", city)
            .queryParam("key", apikey)
            .build())
        .retrieve()
        .onStatus(
            status -> status.is4xxClientError(), 
            response -> Mono.error(new CityNotFound("City not found"))
        )
        .bodyToMono(ApiResponse.class)
        .map(apiresponse -> new WeatherData(
            apiresponse.location().name(),
            apiresponse.location().country(),
            apiresponse.current().temp(),
            apiresponse.current().feelsLike(),
            apiresponse.current().humidity(),
            apiresponse.current().windSpeed(),
            apiresponse.current().condition().text(),
            apiresponse.location().localtime()
        ))
        .onErrorResume(WebClientResponseException.class, e -> 
            // Let the global exception handler handle this
            Mono.error(e)
        );
}

@Override
public Mono<WeeklyForecast> getweeklyforecast(String city) {
    if (city == null || city.trim().isEmpty()) {
        return Mono.error(new IllegalArgumentException("City parameter is required"));
    }
    
    return webClient
        .get()
        .uri(uriBuilder -> uriBuilder
            .path("/forecast.json")
            .queryParam("q", city)
            .queryParam("key", apikey)
            .queryParam("days", 7)
            .build())
        .retrieve()
        .onStatus(
            status -> status.is4xxClientError(), 
            response -> Mono.error(new CityNotFound("City not found"))
        )
        .bodyToMono(ForecastResponse.class)
        .map(this::maptoweeklyforecast)
        .onErrorResume(WebClientResponseException.class, e -> 
            
            Mono.error(e)
        );
}

    private WeeklyForecast maptoweeklyforecast(ForecastResponse response){
        List<DailyForecast> dailyForecasts = response.forecast().forecastdays()
            .stream()
            .map(day -> new DailyForecast(
                day.date(),
                day.day().maxtemp(),
                day.day().mintemp(),
                day.day().avgtemp(),
                day.day().humidity(),
                day.day().precipitation(),
                day.day().chanceOfRain(),
                day.day().condition().text(),
                day.day().condition().icon(),
                day.astro().sunrise(),
                day.astro().sunset()
            ))
            .collect(Collectors.toList());
    return new WeeklyForecast(
            response.location().name(),
            response.location().country(),
            dailyForecasts
        );
    }
}
