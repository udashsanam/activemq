package com.learn.activemq.listener;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;
import org.springframework.stereotype.Component;

import java.util.Enumeration;

@Component("test")
public class CustomMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            // ✅ Extract standard JMS headers
            String messageId = message.getJMSMessageID();
            String correlationId = message.getJMSCorrelationID();
            String destination = message.getJMSDestination().toString();
            long timestamp = message.getJMSTimestamp();

            // ✅ Extract custom properties (user-defined headers)
            String routingKey = message.getStringProperty("routingKey");
            Enumeration<String> enumeration= message.getPropertyNames();
            while(enumeration.hasMoreElements()){
                String propertyName = enumeration.nextElement();
                String propertyValue = message.getStringProperty(propertyName);
                System.out.println(propertyName+":"+propertyValue);
            }

            // ✅ Extract body if it's a TextMessage
            if (message instanceof TextMessage) {
                String text = ((TextMessage) message).getText();
                System.out.println("Message body: " + text);
            }

//            // ✅ Log the headers/properties
//            System.out.println("JMSMessageID: " + messageId);
//            System.out.println("JMSCorrelationID: " + correlationId);
//            System.out.println("JMSDestination: " + destination);
//            System.out.println("JMSTimestamp: " + timestamp);
//            System.out.println("routingKey: " + routingKey);

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
