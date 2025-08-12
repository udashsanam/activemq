package com.learn.activemq;

import com.learn.activemq.model.Employee;
import com.learn.activemq.producer.JmsProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ActivemqApplication {

    @Autowired
    private JmsProducer jmsProducer;

    public static void main(String[] args) {
        SpringApplication.run(ActivemqApplication.class, args);
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void onReady() throws Exception {
//
////        Employee employee = new Employee();
////        employee.setAddress("test");
////        employee.setName("test");
////        jmsProducer.sendMessage(employee);
//        jmsProducer.sendUsingRawJms();
//    }

}
