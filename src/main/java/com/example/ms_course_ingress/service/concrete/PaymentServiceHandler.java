package com.example.ms_course_ingress.service.concrete;

import com.example.ms_course_ingress.dao.repository.PaymentRepository;
import com.example.ms_course_ingress.exception.NotFoundException;
import com.example.ms_course_ingress.model.request.CreatePaymentRequest;
import com.example.ms_course_ingress.model.response.PaymentResponse;
import com.example.ms_course_ingress.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.example.ms_course_ingress.mapper.PaymentMapper.PAYMENT_MAPPER;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceHandler implements PaymentService {

    private final PaymentRepository paymentRepository;


    @Override
    public void createPayment(CreatePaymentRequest request) {
        log.info("ActionLog.createPayment.start request: {}", request);
        paymentRepository.save(PAYMENT_MAPPER.buildPaymentEntity(request));
        log.info("ActionLog.createPayment.success request: {}", request);
    }

    @Override
    public PaymentResponse getPayment(Long id) {
        log.info("ActionLog.createPayment.start request: {}", id);
        return paymentRepository.findById(id)
                .map(PAYMENT_MAPPER ::buildPaymentResponse)
                .orElseThrow(()-> new NotFoundException("Payment not found"));

    }

}
