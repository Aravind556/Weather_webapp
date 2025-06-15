package com.example.Weather_forecast.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

@Controller
public class HomeController {
    
    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    public Mono<ResponseEntity<Resource>> index() {
        Resource indexHtml = new ClassPathResource("static/index.html");
        
        return Mono.just(
            ResponseEntity.ok()
                .contentType(MediaType.TEXT_HTML)
                .body(indexHtml)
        );
    }
}
