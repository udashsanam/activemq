package com.learn.activemq.example;

import com.learn.activemq.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MessageServiceExample {

    @Autowired
    private MessageService messageService;

    public void sendExampleMessage() {
        Map<String, String> headers = new HashMap<>();
        headers.put("messageType", "ORDER");
        headers.put("priority", "HIGH");
        headers.put("source", null); // This will be skipped
        headers.put("timestamp", ""); // This will be skipped
        headers.put("userId", "12345");

        messageService.sendMessage("order.queue", "Order processed successfully", headers);
    }
}