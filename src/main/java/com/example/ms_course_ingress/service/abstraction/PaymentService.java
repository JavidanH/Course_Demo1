package com.example.ms_course_ingress.service.abstraction;

import com.example.ms_course_ingress.model.request.CreatePaymentRequest;
import com.example.ms_course_ingress.model.response.PaymentResponse;

import java.util.List;

public interface PaymentService {
    void createPayment(CreatePaymentRequest request);

    PaymentResponse getPayment(Long id);

    void updatePaymentDescription(Long id, String description);

    void deletePayment(Long id);

    List<PaymentResponse> getPayments();

}
