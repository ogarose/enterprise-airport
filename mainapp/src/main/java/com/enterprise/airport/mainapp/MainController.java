package com.enterprise.airport.mainapp;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/")
    public String indexPage() {
        return "Hello, world! Version 1.1";
    }
}
