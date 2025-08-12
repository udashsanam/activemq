package com.learn.activemq.service;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MessageService {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendMessage(String destination, String body, Map<String, String> headers) {
        jmsTemplate.send(destination, session -> {
            Message message = session.createTextMessage(body);
            
            if (headers != null) {
                headers.entrySet().stream()
                    .filter(entry -> entry.getKey() != null && 
                            entry.getValue() != null && 
                            !entry.getValue().isEmpty())
                    .forEach(entry -> {
                        try {
                            message.setStringProperty(entry.getKey(), entry.getValue());
                        } catch (JMSException e) {
                            throw new RuntimeException("Failed to set message property", e);
                        }
                    });
            }
            
            return message;
        });
    }
}