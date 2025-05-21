package com.example.ChallengeSprint1.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.example.ChallengeSprint1.config.RabbitMQConfig.QUEUE_NAME;

@Service
public class RabbitMQConsumer {

    @RabbitListener(queues = QUEUE_NAME)
    public void receiveMessage(String message) {
        System.out.println("Mensagem recebida da fila: " + message);
    }
}
