package com.example.kafka101;

import com.example.models.Customer;
import com.example.models.MessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@Slf4j
public class KafkaListeners {
    @KafkaListener(topics = "topic1", groupId = "1", properties = {
            "spring.json.value.default.type=com.example.models.MessageRequest",
            "aes.deserializer.secret=${salah.kafka.topics.topic1.secret:}",
            "aes.deserializer.salt=${salah.kafka.topics.topic1.salt:}"
    })
    void listener(MessageRequest data) {
        log.info("Listener received: " + data + " 🥳🎉");
        log.info("gonna throw an exception at time {}", OffsetDateTime.now());
        throw new RuntimeException("oops");
    }


    @KafkaListener(topics = "topic2", groupId = "1", properties = {
            "spring.json.value.default.type=com.example.models.Customer",
            "aes.deserializer.secret=${salah.kafka.topics.topic2.secret:}",
            "aes.deserializer.salt=${salah.kafka.topics.topic2.salt:}"
    })
    void listenerCustomer(Customer customer) {
        log.info("Listener received: customer" + customer + " 🥳🎉");
    }
}
