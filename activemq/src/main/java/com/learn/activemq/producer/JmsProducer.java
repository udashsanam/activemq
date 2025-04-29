package com.learn.activemq.producer;

import com.learn.activemq.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
            jmsTemplate.convertAndSend("TEST", "hello");
            jmsTemplate.convertAndSend("test", "hello");
            jmsTemplate.convertAndSend("TEST", "hello");
            jmsTemplate.convertAndSend("TEST", "hello");
            jmsTemplate.convertAndSend("TEST", "hello");
            jmsTemplate.convertAndSend("TEST", "hello");
        } catch(Exception e){
           log.error("Recieved Exception during send Message: ", e);
        }
    }
}