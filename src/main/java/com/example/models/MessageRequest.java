package com.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MessageRequest {
    private String message;

    public MessageRequest(String message) {
        this.message = message;
    }
}
