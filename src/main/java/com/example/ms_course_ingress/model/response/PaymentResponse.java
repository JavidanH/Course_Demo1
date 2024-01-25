package com.example.ms_course_ingress.model.response;

import com.example.ms_course_ingress.model.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponse {
    private Long id;
    private PaymentStatus status;
    private BigDecimal amount;
    private String description;
}
