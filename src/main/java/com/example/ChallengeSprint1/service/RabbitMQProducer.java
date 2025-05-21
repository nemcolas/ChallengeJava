package com.example.ChallengeSprint1.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.example.ChallengeSprint1.config.RabbitMQConfig.EXCHANGE_NAME;
import static com.example.ChallengeSprint1.config.RabbitMQConfig.ROUTING_KEY;

@Service
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY, message);
        System.out.println("Mensagem enviada para a fila: " + message);
    }
}
