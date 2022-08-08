package com.example.kafka101.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties("salah.kafka")
public class MyKafkaProperties {

    private Map<String, KafkaTopic> topics = new HashMap<>();

    public KafkaTopic getTopic(String topicName) {
        return topics.get(topicName);
    }

    @Data
    public static class KafkaTopic {
        private String topic;
        private String secret;
        private String salt;
        private String groupId;
    }
}
