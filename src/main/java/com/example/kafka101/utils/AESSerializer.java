package com.example.kafka101.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.util.StringUtils;

import java.util.Map;

@Slf4j
public class AESSerializer<T> extends JsonSerializer<T> {

    public static final String SECRET_KEY = "aes.serializer.secret";
    public static final String SALT_KEY = "aes.serializer.salt";

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
    public byte[] serialize(String topic, T data) {
        byte[] json = super.serialize(topic, data);
        return aes != null ? aes.encrypt(json) : json;
    }
}
