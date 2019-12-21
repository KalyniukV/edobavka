package com.kalyniuk.controller;


import com.kalyniuk.service.ViberBotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
    private String userId = "OGdRykRL2GmdB2xKg+NE5g==";

    @Autowired
    ViberBotService service;

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @PostMapping("/")
    public String sendMessage(@RequestParam String message) {
        service.sendMessage(userId, message);
        return "index";
    }
}
