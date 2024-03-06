package com.example.ms_course_ingress.controller;

import com.example.ms_course_ingress.model.response.PaymentResponse;
import com.example.ms_course_ingress.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("internal/v1/payments")
@RequiredArgsConstructor
public class InternalPaymentController {

    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public PaymentResponse getPayment(@PathVariable Long id){
        return paymentService.getPayment(id);
    }
}
