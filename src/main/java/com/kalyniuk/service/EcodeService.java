package com.kalyniuk.service;

import org.springframework.stereotype.Service;

@Service
public class EcodeService {

    String get100() {
        return "Куркуміни. Куркумін, куркума довга";
    }

    String get200() {
        return "Сорбінова кислота";
    }

    String get300() {
        return "Аскорбінова кислота";
    }

    String get400() {
        return "Альгінова кислота";
    }
}
