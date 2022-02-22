package com.enes.consumer.controller;

import com.enes.consumer.listener.KafkaListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("consumer")
public class ConsumerController {

    @Autowired
    private KafkaListeners kafkaListeners;

    @GetMapping("data")
    public List<String> deneme(){
        List<String> data = kafkaListeners.getDataList();
        return data;
    }

}
