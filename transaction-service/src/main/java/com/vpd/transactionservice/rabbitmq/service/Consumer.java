package com.vpd.transactionservice.rabbitmq.service;//package com.saultech.sureproductservice.rabbitmq.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;


@Service
@Slf4j
@RequiredArgsConstructor
public class Consumer {

    @RabbitListener(queues = "notification_queue",autoStartup = "true")
    public void consumeNotification(String message) {
        log.info("Notification: {}", message);
    }

}
