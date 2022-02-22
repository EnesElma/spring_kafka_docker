package com.enes.producer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("kafka")
public class SendMessage {

    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping("{text}")
    public String sendMesagge(@PathVariable String text){
        kafkaTemplate.send("myTopic",text);
        return text + " -> mesajınız kafkaya gönderildi.";
    }
}
