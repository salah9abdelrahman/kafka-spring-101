package com.example.kafka101;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(topics = "salah", groupId = "bla")
    void listener(String data){
        System.out.println("Listener received: " + data + " 🥳🎉");
    }
}
