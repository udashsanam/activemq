package com.learn.activemq.listener;

import com.learn.activemq.model.Employee;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class JmsConsumer  {


    @JmsListener(destination = "test")
    public void onMessage(String message) {
        try {
            String employee = message.toString();
            //do additional processing
            log.info("Received Message: " + employee);
        } catch (Exception e) {
            log.error("Received Exception : " + e);
        }

    }

    @JmsListener(destination = "TEST")
    public void onMessageTEST(String message) {
        try {
            String employee = message.toString();
            //do additional processing
            log.info("Received Message: " + employee);
        } catch (Exception e) {
            log.error("Received Exception : " + e);
        }

    }

}