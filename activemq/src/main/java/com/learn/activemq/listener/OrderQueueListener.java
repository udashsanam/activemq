package com.learn.activemq.listener;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component("order")
public class OrderQueueListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                String text = ((TextMessage) message).getText();
                System.out.println("Received from order.queue: " + text);
                
                String messageType = message.getStringProperty("messageType");
                String priority = message.getStringProperty("priority");
                String userId = message.getStringProperty("userId");
                String source = message.getStringProperty("source");
                System.out.println("Received from order.queue source : " + source);


                System.out.println("Headers - messageType: " + messageType + 
                                 ", priority: " + priority + 
                                 ", userId: " + userId);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}