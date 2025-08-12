package com.learn.activemq.config;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.MessageListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import java.util.Arrays;

@Configuration
@EnableJms
public class ActiveMQConfig {

    @Value("${active-mq.broker-url}")
    private String brokerUrl;


    @Bean
    public ConnectionFactory connectionFactory(){
        ActiveMQConnectionFactory activeMQConnectionFactory  = new ActiveMQConnectionFactory();
        activeMQConnectionFactory.setBrokerURL(brokerUrl);
        activeMQConnectionFactory.setUserName("admin");
        activeMQConnectionFactory.setPassword("admin");

        return  activeMQConnectionFactory;
    }

    @Bean
    public JmsTemplate jmsTemplate(){
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setPubSubDomain(false);
        return jmsTemplate;
    }



    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(){
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPubSubDomain(true);
        return factory;
    }

    @Bean
    public DefaultMessageListenerContainer listenerContainer(ConnectionFactory connectionFactory,
                                                             @Qualifier("test") MessageListener listener,  @Qualifier("order") MessageListener listene) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName("MyQueue");
        container.setMessageListener(listener);

        container.setConnectionFactory(connectionFactory);
        container.setDestinationName("order.queue");
        container.setMessageListener(listene);
        return container;
    }




}
