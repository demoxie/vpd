package com.vpd.transactionservice.rabbitmq.service;

import com.vpd.transactionservice.config.app.AppConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class Producer {
    private final RabbitTemplate rabbitTemplate;
    private final AppConfig appConfig;

    public void sendNotification(String message) {
        rabbitTemplate.convertAndSend(appConfig.getNotification().getQueue(), message);
    }

}
