package com.vpd.transactionservice.config.rabbitmq;


import com.vpd.transactionservice.config.app.AppConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class RabbitMQConfig {
    private final AppConfig appConfig;
    private final RabbitmqPropertyConfig rabbitmqPropertyConfig;
    @Bean
    public Queue notificationQueue() {
        return new Queue(appConfig.getNotification().getQueue(), true);
    }


    //Create dead letter queue
    @Bean
    public Queue notificationDeadLetterQueue() {
        return QueueBuilder.durable(appConfig.getNotification().getDeadLetterQueue())
                .withArgument("x-dead-letter-exchange", appConfig.getNotification().getExchange())
                .withArgument("x-dead-letter-routing-key", appConfig.getNotification().getRoutingKey())
                .build();
    }

    @Bean
    public Exchange notificationExchange() {
        return new DirectExchange(appConfig.getNotification().getExchange());
    }


    @Bean
    public Binding notificationBinding() {
        return BindingBuilder.bind(notificationQueue()).to(notificationExchange()).with(appConfig.getNotification().getRoutingKey()).noargs();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    @Primary
    public RabbitTemplate rabbitmqTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitmqTemplate = new RabbitTemplate();
        rabbitmqTemplate.setConnectionFactory(connectionFactory);
        rabbitmqTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitmqTemplate;
    }

    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }
}
