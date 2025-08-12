package com.learn.activemq.producer;

import com.learn.activemq.model.Employee;
import jakarta.jms.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;



@Component
@Slf4j
public class JmsProducer {

    @Autowired
    JmsTemplate jmsTemplate;

    private String topic;

    public void sendMessage(Employee message){
        try{
            log.info("Attempting Send message to Topic: "+ topic);
                    jmsTemplate.convertAndSend("myQueue", message.toString());
        } catch(Exception e){
           log.error("Recieved Exception during send Message: ", e);
        }
    }

    public void sendUsingRawJms() throws Exception {
        ConnectionFactory connectionFactory = jmsTemplate.getConnectionFactory();

        try (Connection connection = connectionFactory.createConnection()) {
            connection.start();

            try (Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)) {
                // Create a Destination (Queue or Topic)
                Destination destination = session.createQueue("MyQueue");
                jmsTemplate.convertAndSend(destination, "Hello World", (messages) -> {
                    messages.setStringProperty("routingKey", "/RPO/SWFPP");
                    return messages;
                });

                // Create producer and send message
//                MessageProducer producer = session.createProducer(destination);
//                TextMessage message = session.createTextMessage("Hello from raw JMS with JmsTemplate's ConnectionFactory");
//                producer.send(message);
            }
        }
    }
}