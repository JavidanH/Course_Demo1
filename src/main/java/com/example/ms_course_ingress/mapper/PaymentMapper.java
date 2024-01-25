package com.example.ms_course_ingress.mapper;

import com.example.ms_course_ingress.dao.entity.PaymentEntity;
import com.example.ms_course_ingress.model.enums.PaymentStatus;
import com.example.ms_course_ingress.model.request.CreatePaymentRequest;
import com.example.ms_course_ingress.model.response.PaymentResponse;

public enum PaymentMapper {

    PAYMENT_MAPPER;

    public PaymentEntity buildPaymentEntity(CreatePaymentRequest payment) {
        return PaymentEntity
                .builder()
                .amount(payment.getAmount())
                .description((payment.getDescription()))
                .status(PaymentStatus.DRAFT)
                .build();

    }

    public PaymentResponse buildPaymentResponse(PaymentEntity payment){
        return PaymentResponse.builder()
                .id(payment.getId())
                .description(payment.getDescription())
                .amount(payment.getAmount())
                .status(payment.getStatus())
                .build();
    }
}
