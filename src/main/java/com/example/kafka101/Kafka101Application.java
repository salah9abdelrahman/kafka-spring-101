package com.example.kafka101;

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

    @Bean
    CommandLineRunner commandLineRunner(KafkaTemplate<String, String> kafkaTemplate){
        return args -> {
          kafkaTemplate.send("salah", "hello yo!! my man");
        };
    }

}
