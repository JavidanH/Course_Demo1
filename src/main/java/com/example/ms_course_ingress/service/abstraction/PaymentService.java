package com.example.ms_course_ingress.service.abstraction;

import com.example.ms_course_ingress.model.request.CreatePaymentRequest;
import com.example.ms_course_ingress.model.response.PaymentResponse;

public interface PaymentService {
    void createPayment(CreatePaymentRequest request);

    PaymentResponse getPayment(Long id);

}
