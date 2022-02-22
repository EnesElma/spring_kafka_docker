package com.enes.consumer.listener;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KafkaListeners {

    private final List<String> dataList = new ArrayList<>();

    @KafkaListener(topics = "myTopic", groupId = "myGroupId")
    public void listener(String data){
        dataList.add(data);
    }

    public List<String> getDataList() {
        return dataList;
    }

}
