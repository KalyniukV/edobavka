package com.kalyniuk.controller;


import com.viber.bot.api.ViberBot;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.inject.Inject;


@Controller
public class MainController {

    @Inject
    private ViberBot bot;

    @Value("${application.viber-bot.webhook-url}")
    private String webhookUrl;

    @GetMapping("/")
    public String main() {
        System.out.println("=== MainController.main() ===");
        return "index";
    }

    @PostMapping("/")
    public String sendMessage(@RequestParam String text) {
        System.out.println("=== MainController.sendMessage text = " + text);
        try {
            bot.setWebhook(webhookUrl).get();

            System.out.println("=== MainController accountInfo = " + bot.getAccountInfo().get());

        } catch (Exception e) {
            System.out.println("=== MainController.sendMessage error = " + e.getMessage() + "\n" + e);
        }

        return "index";
    }
}
