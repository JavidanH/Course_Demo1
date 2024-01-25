package com.example.ms_course_ingress.controller;


import com.example.ms_course_ingress.model.request.CreatePaymentRequest;
import com.example.ms_course_ingress.model.response.PaymentResponse;
import com.example.ms_course_ingress.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createPayment(@RequestBody CreatePaymentRequest request) {
        paymentService.createPayment(request);
    }

    @GetMapping("/{id}")
    public PaymentResponse getPayment(@PathVariable Long id){
        return paymentService.getPayment(id);
    }
}
