package com.kalyniuk.controller;

import com.viber.bot.Request;
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
    public String sendMessage(@RequestParam String message) {
        System.out.println("=== send message = " + message);
        String jsonString = "{\"event\":\"message\","+
                "\"receiver\":\"OGdRykRL2GmdB2xKg+NE5g==\"," +
                "\"min_api_version\":1," +
                "\"sender\":{\n" +
                "\"name\":\"edobavka\"," +
                "\"avatar\":\"https://images-na.ssl-images-amazon.com/images/I/51-aTeYbibL._SY355_.png\"" +
                "}," +
                "\"tracking_data\":\"tracking data\"," +
                "\"type\":\"text\"," +
                "\"text\":\"Hello world!\"" +
                "}";
        System.out.println("=== jsonString = " + jsonString);
        bot.incoming(Request.fromJsonString(jsonString));
        return "index";
    }
}
