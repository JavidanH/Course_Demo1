package com.example.ms_course_ingress.service.concrete;

import com.example.ms_course_ingress.dao.entity.PaymentEntity;
import com.example.ms_course_ingress.dao.repository.PaymentRepository;
import com.example.ms_course_ingress.exception.NotFoundException;
import com.example.ms_course_ingress.model.request.CreatePaymentRequest;
import com.example.ms_course_ingress.model.response.PaymentResponse;
import com.example.ms_course_ingress.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import static com.example.ms_course_ingress.mapper.PaymentMapper.PAYMENT_MAPPER;
import static com.example.ms_course_ingress.model.enums.PaymentStatus.DELETED;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentServiceHandler implements PaymentService {

    private final PaymentRepository paymentRepository;

   // private final AsyncService asyncService;


    @Override
    public void createPayment(CreatePaymentRequest request) {
        log.info("ActionLog.createPayment.start request: {}", request);
        paymentRepository.save(PAYMENT_MAPPER.buildPaymentEntity(request));
        log.info("ActionLog.createPayment.success request: {}", request);
    }

    @Override
    @Cacheable("payments")
    public PaymentResponse getPayment(Long id) {
        log.info("ActionLog.createPayment.start request: {}", id);
        var payment = fetchPaymentIfExist(id);
        return PAYMENT_MAPPER.buildPaymentResponse(payment);

    }

    @Override
    public void updatePaymentDescription(Long id, String description) {
        var payment = fetchPaymentIfExist(id);
        payment.setDescription(description);
        paymentRepository.save(payment);

    }

    @Override
    public void deletePayment(Long id) {
        var payment = fetchPaymentIfExist(id);
        payment.setStatus(DELETED);
        paymentRepository.save(payment);
    }

    @Override
    public void test() {

    }

//    @Override
//    public List<PaymentResponse> getPayments() {
//        return paymentRepository.findAll().stream()
//                .map(PAYMENT_MAPPER::buildPaymentResponse)
//                .collect(toList());
//    }



//    @Override
//    @SneakyThrows
//    @Async
//    public void test() {
//        log.info("Logic Start");
//        asyncService.test2();
//        log.info("Logic End");
//    }

    @Override
    public void deleteCache() {
    }


    private PaymentEntity fetchPaymentIfExist(Long id) {
        return paymentRepository.findByIdAndStatusNot(id,DELETED)
                .orElseThrow(() -> new NotFoundException("Payment not found"));
    }

}
