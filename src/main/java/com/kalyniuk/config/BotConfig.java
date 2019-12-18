package com.kalyniuk.config;

import com.viber.bot.ViberSignatureValidator;
import com.viber.bot.api.ViberBot;
import com.viber.bot.profile.BotProfile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Nullable;

@Configuration
public class BotConfig {
    @Value("${application.viber-bot.auth-token}")

    private  String authorizationToken;
    @Value("${application.viber-bot.name}")
    private String name;

    @Nullable
    @Value("${application.viber-bot.avatar:@null}")
    private String avatar;


    @Bean
    ViberBot viberBot() {
        return  new ViberBot(new BotProfile(name,avatar),authorizationToken);
    }

    @Bean
    ViberSignatureValidator signatureValidator() {
        return new ViberSignatureValidator(authorizationToken);
    }
}
