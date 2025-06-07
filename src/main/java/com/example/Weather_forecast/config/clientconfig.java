package com.example.Weather_forecast.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class clientconfig {

    @Bean

    public WebClient weatherwebClient(){
        return WebClient.builder()
                .baseUrl("https://api.weatherapi.com/v1")
                .build();
    }
    
}
