package com.kalyniuk.controller;


import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @PostMapping("/webhook")
    public String sendMessage(@RequestParam String message) {
        System.out.println("send message = " + message);        
        return "index";
    }
}
