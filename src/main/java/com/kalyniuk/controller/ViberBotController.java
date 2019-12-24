package com.kalyniuk.controller;

import com.google.common.base.Charsets;
import com.google.common.io.CharStreams;
import com.viber.bot.Request;
import com.viber.bot.ViberSignatureValidator;
import com.viber.bot.api.ViberBot;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

@RestController
public class ViberBotController {

    @Inject
    private ViberBot bot;

    @Inject
    private ViberSignatureValidator viberSignatureValidator;

    @PostMapping(value = "/webhook", produces = "application/json")
    public String incoming(@RequestBody String json,
                           @RequestHeader("X-Viber-Content-Signature") String serverSideSignature)
            throws ExecutionException, InterruptedException, IOException {
        com.google.common.base.Preconditions.checkState(viberSignatureValidator.isSignatureValid(serverSideSignature, json), "invalid signature");
        @javax.annotation.Nullable InputStream response = bot.incoming(Request.fromJsonString(json)).get();
getStream(response);
        return response != null ? CharStreams.toString(new InputStreamReader(response, Charsets.UTF_16)) : null;
    }
    
    private void getStream(InputStream inputStream) {
        System.out.println("=== getStream ===");
        try {
            InputStreamReader isReader = new InputStreamReader(inputStream);
            //Creating a BufferedReader object
            BufferedReader reader = new BufferedReader(isReader);
            StringBuffer sb = new StringBuffer();
            String str;
            while((str = reader.readLine())!= null){
                sb.append(str);
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
