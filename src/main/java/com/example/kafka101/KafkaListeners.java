package com.example.kafka101;

import com.example.models.Customer;
import com.example.models.MessageRequest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {
    @KafkaListener(topics = "topic1", groupId = "1", properties = {
            "spring.json.value.default.type=com.example.models.MessageRequest",
            "aes.deserializer.secret=${salah.kafka.topics.topic1.secret:}",
            "aes.deserializer.salt=${salah.kafka.topics.topic1.salt:}"
    })
    void listener(MessageRequest data) {
        System.out.println("Listener received: " + data + " 🥳🎉");
    }


    @KafkaListener(topics = "topic2", groupId = "1", properties = {
            "spring.json.value.default.type=com.example.models.Customer",
            "aes.deserializer.secret=${salah.kafka.topics.topic2.secret:}",
            "aes.deserializer.salt=${salah.kafka.topics.topic2.salt:}"
    })
    void listenerCustomer(Customer customer) {
        System.out.println("Listener received: customer" + customer + " 🥳🎉");
    }
}
