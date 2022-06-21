package com.example.kafka101;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.ExecutionException;

@SpringBootTest
@EmbeddedKafka(topics = "salah",
        bootstrapServersProperty = "spring.kafka.bootstrap-servers")
@DirtiesContext
class Kafka101ApplicationTests {

    @Autowired
    private KafkaTemplate<String, String> template;

    @Test
    void contextLoads() {
    }

    @Test
    void testSendMessage() throws ExecutionException, InterruptedException {
        ListenableFuture<SendResult<String, String>>  send = template.send("salah", "ah ha");
        System.out.println(send.get().toString());
    }

}
