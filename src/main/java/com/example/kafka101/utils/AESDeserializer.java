package com.example.kafka101.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.header.Headers;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
public class AESDeserializer<T> extends JsonDeserializer<T> {
    public static final String SECRET_KEY = "aes.deserializer.secret";
    public static final String SALT_KEY = "aes.deserializer.salt";

    private AES aes;

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        super.configure(configs, isKey);
        String secret = (String) configs.get(SECRET_KEY);
        String salt = (String) configs.get(SALT_KEY);
        if (StringUtils.hasLength(secret) && StringUtils.hasLength(salt)) {
            aes = new AES(secret, salt);
        }
    }

    @Override
    public T deserialize(String topic, Headers headers, byte[] data) {
        return super.deserialize(topic, headers, decryptTopic(data));
    }

    @Override
    public T deserialize(String topic, byte[] data) {
        return super.deserialize(topic, decryptTopic(data));
    }

    private byte[] decryptTopic(byte[] data) {
        return aes != null ? aes.decrypt(data) : data;
    }
}
