package com.example.Weather_forecast.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.result.view.RedirectView;



@Controller
public class Content {

    @GetMapping("/")
    public RedirectView welcome() {
        return new RedirectView("index.html");
    }
    
    
}
