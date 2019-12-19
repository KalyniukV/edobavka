package com.kalyniuk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "index";
    }
    
    @PostMapping("/")
    public String sendMessage(@RequestParam String message) {
        System.out.println("send message = " + message);
        return "index";
    }

}
