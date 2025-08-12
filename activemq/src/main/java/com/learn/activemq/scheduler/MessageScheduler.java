package com.learn.activemq.scheduler;

import com.learn.activemq.example.MessageServiceExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MessageScheduler {

    @Autowired
    private MessageServiceExample messageServiceExample;

    @Scheduled(fixedRate = 30000)
    public void scheduleMessageSending() {
        messageServiceExample.sendExampleMessage();
    }
}