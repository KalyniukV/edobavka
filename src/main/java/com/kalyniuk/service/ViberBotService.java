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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.Future;

@Service
public class ViberBotService {

    @Autowired
    private EcodeService ecodeService;


    public Future<Optional<Message>> onConversationStarted(IncomingConversationStartedEvent event) {

        TrackingData trackingData = createTrackingData("onConversationStarted", null,null,null,null,
                null,null,null,null,
                null,null,null,null);

        ArrayList<Object> buttonArray = new ArrayList<Object>();
        buttonArray.add(makeButtons(2,3,"100", "reply", "100"));
        buttonArray.add(makeButtons(2,3,"200", "reply", "200"));
        buttonArray.add(makeButtons(2,3,"300", "reply", "300"));
        buttonArray.add(makeButtons(2,3,"400", "reply", "400"));

        MessageKeyboard keyboard = createKeyboard(buttonArray);

        UserProfile user = event.getUser();
//        userService.add(new User(user.getId(), user.getName(), true));

        return Futures.immediateFuture(
                Optional.of(new TextMessage("Hi " + event.getUser().getName() + ", " +
                        "please choose one of the options below: ",
                        keyboard, trackingData)));

    }

//    public void onMessageReceived(IncomingMessageEvent event, Message message, Response response) {
//
//        switch (message.getTrackingData().get("step").toString()) {
//            case "onConversationStarted":
//                onConversationStartedResponse(event, message, response);
//                break;
//            case "applyToRoute":
//                System.out.println("USAO U APPLY");
//                applyToRoute(event, message, response);
//                break;
//            case "createRoute":
//                System.out.println("USAO U CREATE");
//                createRoute(event, message, response);
//                break;
//        }
//    }

    public void onSubscribe(IncomingSubscribedEvent event, Response response) {
        UserProfile user = event.getUser();
//        userService.add(new User(user.getId(), user.getName(), true));

        response.send(new TextMessage("You are now subscribed"));
    }

    public void onUnsubscribe(IncomingUnsubscribeEvent event) {
//        userService.unsubscribe(event.getUserId());

    }

    private TrackingData createTrackingData(String event, String key1, String value1,
                                            String key2, String value2,
                                            String key3, String value3,
                                            String key4, String value4,
                                            String key5, String value5,
                                            String key6, String value6) {
        Map<String, Object> tdMap = new HashMap<String, Object>();
        tdMap.put("step", event);
        if(key1 != null)
            tdMap.put(key1, value1);
        if(key2 != null)
            tdMap.put(key2, value2);
        if(key3 != null)
            tdMap.put(key3, value3);
        if(key4 != null)
            tdMap.put(key4, value4);
        if(key5 != null)
            tdMap.put(key5, value5);
        if(key6 != null)
            tdMap.put(key6, value6);
        TrackingData trackingData = new TrackingData(tdMap);

        return trackingData;
    }


    private MessageKeyboard createKeyboard(ArrayList<Object> buttons) {
        Map<String, Object> keyboardMap = new HashMap<String, Object>();
        keyboardMap.put("Type", "keyboard");
        keyboardMap.put("Buttons", buttons);
        return new MessageKeyboard(keyboardMap);
    }


    private Map<String, Object> makeButtons(Integer rows, Integer columns, String text,
                                            String actionType , String actionBody) {
        Map<String, Object> button = new HashMap<String, Object>();

        button.put("Columns", columns);
        button.put("Rows", rows);
        button.put("Text", "<font color='#494E67'>" + text + "</font>");
        button.put("TextSize", "medium");
        button.put("TextHAlign", "center");
        button.put("TextVAlign", "center");
        button.put("ActionType", actionType);
        button.put("ActionBody", actionBody);
        //button.put("ButtonColor",Bgcolor);
        return button;
    }


}
