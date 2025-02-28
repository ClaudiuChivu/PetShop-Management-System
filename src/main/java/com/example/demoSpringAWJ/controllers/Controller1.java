package com.example.demoSpringAWJ.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Controller1 {
    @GetMapping("/")
    public String home() {
        return "home";  // Încărcăm fișierul home.html
    }
}
