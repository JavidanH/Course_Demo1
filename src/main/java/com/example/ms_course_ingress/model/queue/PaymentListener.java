package com.example.ms_course_ingress.model.queue;


import com.example.ms_course_ingress.model.request.CreatePaymentRequest;
import com.example.ms_course_ingress.service.abstraction.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentListener {

    private final PaymentService paymentService;
    private final ObjectMapper objectMapper;

    @RabbitListener(queues = "PAYMENT_Q")

    public void consume(String data){

        CreatePaymentRequest paymentRequest = null;
        try {
            paymentRequest = objectMapper.readValue(data, CreatePaymentRequest.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        paymentService.createPayment(paymentRequest);
    }

}
