package com.example.ms_course_ingress.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final String paymentQ;
    private final String paymentDLQ;
    private final String paymentQExchange;
    private final String paymentDLQExchange;
    private final String paymentQKey;
    private final String paymentDLQKey;

    public RabbitMQConfig(@Value("${rabbitmq.queue.payment}") String paymentQ,
                          @Value("${rabbitmq.queue.payment-dlq}") String paymentDLQ) {
        this.paymentQ = paymentQ;
        this.paymentDLQ = paymentDLQ;
        this.paymentQExchange = paymentQ + "_Exchange";
        this.paymentDLQExchange = paymentDLQ + "_Exchange";
        this.paymentQKey = paymentQ + "_Key";
        this.paymentDLQKey = paymentDLQ + "_Key";
    }

    @Bean
    public DirectExchange paymentDLQExchange() {
        return new DirectExchange(paymentDLQExchange);
    }

    @Bean
    public DirectExchange paymentQExchange() {
        return new DirectExchange(paymentQExchange);
    }

    @Bean
    public Queue paymentDQL() {
        return new Queue(paymentDLQ, true);
    }

    @Bean
    public Queue paymentQ() {
        return new Queue(paymentQ, true);
    }

    @Bean
    public Binding paymentDLQBinding() {
        return BindingBuilder.bind(paymentDQL())
                .to(paymentDLQExchange())
                .with(paymentDLQKey);
    }

    @Bean
    public Binding paymentQBinding() {
        return BindingBuilder.bind(paymentQ())
                .to(paymentQExchange())
                .with(paymentQKey);
    }
}
