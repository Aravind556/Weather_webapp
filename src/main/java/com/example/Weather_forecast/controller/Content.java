package com.example.Weather_forecast.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.result.view.RedirectView;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class Content {

    @GetMapping("/page")
    public RedirectView welcome() {
        return new RedirectView("index.html");
    }
    
    
}
