package com.kalyniuk.service;

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
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;

@Service
public class ViberBotService {
    private static Logger logger = LoggerFactory.getLogger(ViberBotService.class);

    @Autowired
    private EcodeService ecodeService;


    public void onMessageReceived(IncomingMessageEvent event, Message message, Response response) {
        logger.debug("ViberBotService.onMessageReceived message = " + message);
        switch (message.toString()) {
            case "100":
                response.send(ecodeService.get100());
                break;
            case "200":
                ecodeService.get200();
                break;
            case "300":
                ecodeService.get300();
                break;
            case "400":
                ecodeService.get400();
                break;
            default:
                response.send("невідома добавка");
        }

    }


}
