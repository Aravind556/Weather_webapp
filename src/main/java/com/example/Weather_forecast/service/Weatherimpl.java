package com.example.Weather_forecast.service;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.Weather_forecast.Model.DailyForecast;
import com.example.Weather_forecast.Model.WeatherData;
import com.example.Weather_forecast.Model.WeeklyForecast;
import com.example.Weather_forecast.Model.external.ApiResponse;
import com.example.Weather_forecast.Model.external.ForecastResponse;

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
        // Check for null or empty city parameter
        if (city == null || city.trim().isEmpty()) {
            logger.error("City parameter cannot be null or empty");
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
            .bodyToMono(ApiResponse.class)
            .doOnNext(apiresponse -> logger.info("Received weather data for city: {}", city))
            .map(apiresponse -> new WeatherData(
                apiresponse.location().name(),
                apiresponse.location().country(),
                apiresponse.current().temp(),
                apiresponse.current().feelsLike(),
                apiresponse.current().humidity(),
                apiresponse.current().windSpeed(),
                apiresponse.current().condition().text(),
                apiresponse.location().localtime()
            ));
             
    }

    @Override
    public Mono<WeeklyForecast> getweeklyforecast(String city){
        if(city ==null ||city.trim().isEmpty()){
            logger.error("City empty");
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
            .bodyToMono(ForecastResponse.class)
            .doOnNext(response -> logger.info("Received weekly forecast for city: {}", city))
            .map(this::maptoweeklyforecast);     
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
