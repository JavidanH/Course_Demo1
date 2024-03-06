package com.example.ms_course_ingress.model.scheduler;


import com.example.ms_course_ingress.service.abstraction.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CybernetScheduler {
    private final PaymentService paymentService;

    @Async
    @Scheduled(fixedDelayString = "PT1S")
    public void test3() {
        log.info("Schedule start");
        paymentService.test();
        log.info("Schedule end");
    }
}
