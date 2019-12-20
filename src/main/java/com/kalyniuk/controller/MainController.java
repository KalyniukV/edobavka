package com.kalyniuk.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.Map;


@Controller
public class MainController {

    private static final String URL = "https://chatapi.viber.com/pa/send_message";
    private static final String VIBER_HEADER = "X-Viber-Auth-Token";

    @Value("${application.viber-bot.auth-token}")
    private  String authorizationToken;

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @PostMapping("/")
    public String sendMessage(@RequestParam String message) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(VIBER_HEADER, authorizationToken);
        String userId = "OGdRykRL2GmdB2xKg+NE5g==";
        Map<String, Object> msg = ImmutableMap.of(
                "receiver", userId,
                "type", "text",
                "text", message,
                "sender", ImmutableMap.of("name", "edobavka")
        );
        HttpEntity<Map> request = new HttpEntity<>(msg, headers);

        new RestTemplate().postForEntity(URL, request, Map.class);
        return "index";
    }
}
