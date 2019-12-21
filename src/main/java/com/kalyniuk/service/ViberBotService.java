package com.kalyniuk.service;

import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.Futures;
import com.viber.bot.Response;
import com.viber.bot.event.incoming.IncomingConversationStartedEvent;
import com.viber.bot.event.incoming.IncomingMessageEvent;
import com.viber.bot.event.incoming.IncomingSubscribedEvent;
import com.viber.bot.event.incoming.IncomingUnsubscribeEvent;
import com.viber.bot.message.Message;
import com.viber.bot.message.MessageKeyboard;
import com.viber.bot.message.TextMessage;
import com.viber.bot.message.TrackingData;
import com.viber.bot.profile.UserProfile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.concurrent.Future;

@Service
public class ViberBotService {
    private static final String URL = "https://chatapi.viber.com/pa/send_message";
    private static final String VIBER_HEADER = "X-Viber-Auth-Token";

    @Value("${application.viber-bot.auth-token}")
    private  String authorizationToken;

    @Autowired
    private EcodeService ecodeService;

    public Future<Optional<Message>> onConversationStarted(IncomingConversationStartedEvent event) {
        System.out.println("ViberBotService.onConversationStarted user id = " + event.getUser().getId() + " name = " + event.getUser().getName());
        return Futures.immediateCheckedFuture(Optional.of(new TextMessage("Привіт " + event.getUser().getName())));
    }

    public void onMessageReceived(IncomingMessageEvent event, Message message, Response response) {
        System.out.println("ViberBotService.onMessageReceived message = " + message.getMapRepresentation().get("text"));

        switch (message.getMapRepresentation().get("text").toString()) {
            case "100":
                response.send(ecodeService.get100());
                break;
            case "200":
                response.send(ecodeService.get200());
                break;
            case "300":
                response.send(ecodeService.get300());
                break;
            case "400":
                response.send(ecodeService.get400());
                break;
            default:
                response.send("невідома добавка");
        }
    }

    public void sendMessage(String message) {
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
        System.out.println("=== MainController.sendMessage = " + msg);
        new RestTemplate().postForEntity(URL, request, Map.class);
    }

}
