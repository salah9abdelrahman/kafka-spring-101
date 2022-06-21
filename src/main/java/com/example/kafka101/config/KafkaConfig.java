package com.example.kafka101.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.listener.CommonLoggingErrorHandler;

@Configuration
public class KafkaConfig {
    /*
    The NewTopic bean causes the topic to be created on the broker; it is not needed if the topic already exists.
     */
    @Bean
    public NewTopic helloTopic() {
        return TopicBuilder.name("salah")
                .build();
    }

    @Bean
    public CommonLoggingErrorHandler errorHandler() {
        return new CommonLoggingErrorHandler();
    }
}
