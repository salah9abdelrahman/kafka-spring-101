package com.example.kafka101;

import com.example.models.Customer;
import com.example.models.MessageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
@RequiredArgsConstructor
public class MessageController {

    private final KafkaTemplate<String, MessageRequest> kafkaTemplate;

    private final KafkaTemplate<String, Customer> customerKafkaTemplate;

    @PostMapping
    public void publish(@RequestBody MessageRequest messageRequest){
        kafkaTemplate.send("topic1", messageRequest);
    }

    @PostMapping("/customer")
    public void publishCustomer(@RequestBody Customer customer){
        customerKafkaTemplate.send("topic2", customer);
    }
}
