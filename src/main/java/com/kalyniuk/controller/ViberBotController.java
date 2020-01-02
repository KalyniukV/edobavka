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
import java.util.stream.Collectors;

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
        System.out.println("=== incoming serverSideSignature =  " + serverSideSignature);
        System.out.println("=== incoming json =  " + json);
        com.google.common.base.Preconditions.checkState(viberSignatureValidator.isSignatureValid(serverSideSignature, json), "invalid signature");
        @javax.annotation.Nullable InputStream response = bot.incoming(Request.fromJsonString(json)).get();
        System.out.println("=== incoming response =  " + response);
        return response != null ? CharStreams.toString(new InputStreamReader(response, Charsets.UTF_16)) : null;
    }

}
