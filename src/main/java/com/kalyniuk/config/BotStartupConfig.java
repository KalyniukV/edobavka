package com.kalyniuk.config;

import com.kalyniuk.service.ViberBotService;
import com.viber.bot.api.ViberBot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${application.viber-bot.webhook-url}")
    private String webhookUrl;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
//         try {
//             System.out.println("=== BotStartupConfig onApplicationEvent before setWebhook ===");
//             bot.setWebhook(webhookUrl).get();
//         } catch (Exception e) {
//             System.out.println(e);
//         }
        
        bot.onMessageReceived((event, message, response) -> viberService.onMessageReceived(event, message, response));
        bot.onConversationStarted(event -> viberService.onConversationStarted(event));
    }

}
