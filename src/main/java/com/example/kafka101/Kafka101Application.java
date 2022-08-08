package com.example.kafka101;

import com.example.models.MessageRequest;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;

@SpringBootApplication
public class Kafka101Application {

    public static void main(String[] args) {
        SpringApplication.run(Kafka101Application.class, args);
    }

//    @Bean
//    CommandLineRunner commandLineRunner( KafkaTemplate<String, MessageRequest> kafkaTemplate){
//        return args -> {
//          kafkaTemplate.send("topic2", new MessageRequest("test 1 yaaaaao"));
//        };
//    }

}
