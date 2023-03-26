package com.example.kafka101.config;

import com.example.models.Customer;
import com.example.models.MessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.listener.CommonLoggingErrorHandler;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.ExponentialBackOff;
import org.springframework.util.backoff.FixedBackOff;

import java.util.Map;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class KafkaConfig {


    private final MyKafkaProperties myKafkaProperties;

    private final String TOPIC_1 = "topic1";
    private final String TOPIC_2 = "topic2";


    @Bean
    public KafkaTemplate<String, MessageRequest> messageTemplate(ProducerFactory<String, MessageRequest> pf) {
        MyKafkaProperties.KafkaTopic kafkaProductOrderTopic = myKafkaProperties.getTopic(TOPIC_1);
        return new KafkaTemplate<>(pf,
                Map.of("aes.serializer.secret", kafkaProductOrderTopic.getSecret(),
                        "aes.serializer.salt", kafkaProductOrderTopic.getSalt()
                ));
    }

    @Bean
    @Primary
    public KafkaTemplate<String, Customer> customerTemplate(ProducerFactory<String, Customer> pf) {
        MyKafkaProperties.KafkaTopic kafkaEventSchedulerTopic = myKafkaProperties.getTopic(TOPIC_2);
        return new KafkaTemplate<>(pf,
                Map.of(
                        "aes.serializer.secret", kafkaEventSchedulerTopic.getSecret(),
                        "aes.serializer.salt", kafkaEventSchedulerTopic.getSalt()
                ));
    }


    @Bean
    public DefaultErrorHandler errorHandler() {
        ExponentialBackOff exponentialBackOff = new ExponentialBackOff(2000L, 1.5);
        exponentialBackOff.setMaxElapsedTime(1000L * 60 * 5); // 5 mins

        DefaultErrorHandler errorHandler = new DefaultErrorHandler((consumerRecord, exception) -> {
            // logic to execute when all the retry attemps are exhausted
            log.info("all retries is finished");
        }, exponentialBackOff);
        return errorHandler;
    }

}
