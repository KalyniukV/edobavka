package com.kalyniuk.controller;


import com.viber.bot.api.ViberBot;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;

@Controller
public class MainController {

    @Inject
    private ViberBot bot;

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @PostMapping("/")
    public String sendMessage(@RequestParam String text) {
        try {
            System.out.println("send text = " + text);
            ListenableFuture<ApiResponse> userDetails = bot.getUserDetails("OGdRykRL2GmdB2xKg+NE5g==");
            System.out.println("userDetails = " + userDetails.toString());

        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n" + e);
        }
        return "index";
    }
}
