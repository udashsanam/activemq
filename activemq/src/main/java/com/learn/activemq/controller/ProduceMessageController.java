package com.learn.activemq.controller;

import com.learn.activemq.model.Employee;
import com.learn.activemq.producer.JmsProducer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
public class ProduceMessageController {

    private final JmsProducer jmsProducer;

    @PostMapping(value="/api/employee")
    public Employee sendMessage(@RequestBody Employee employee){
        jmsProducer.sendMessage(employee);
        return employee;
    }
}