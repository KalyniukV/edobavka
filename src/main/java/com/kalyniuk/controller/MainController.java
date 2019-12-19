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
        System.out.println("send text = " + text);
        bot.onTextMessage("(hi|hello)", (event, message, response) -> response.send(text + " " + event.getSender().getName()));
        return "index";
    }
}
