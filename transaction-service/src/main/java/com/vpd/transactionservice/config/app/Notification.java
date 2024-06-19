package com.vpd.transactionservice.config.app;

import lombok.Data;

@Data
public class Notification {
    private String queue;
    private String exchange;
    private String routingKey;
    private String deadLetterQueue;
}
