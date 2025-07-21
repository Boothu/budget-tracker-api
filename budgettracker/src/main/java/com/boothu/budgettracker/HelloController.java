package com.boothu.budgettracker;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String index() {
        // Executes when http://localhost:8080/hello is accessed with a GET request
        return "Spring Boot backend is functional.";
    }
}
