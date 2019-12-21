package com.kalyniuk.config;

import com.kalyniuk.service.ViberBotService;
import com.viber.bot.api.ViberBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import javax.inject.Inject;



@Configuration
public class BotStartupConfig implements ApplicationListener<ApplicationReadyEvent> {

    @Inject
    private ViberBot bot;

    @Autowired
    private ViberBotService viberService;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        bot.onMessageReceived((event, message, response) -> viberService.onMessageReceived(event, message, response));
        bot.onConversationStarted(event -> viberService.onConversationStarted(event));
    }

}
