package com.example.ms_course_ingress.service.concrete;

import lombok.SneakyThrows;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncService {
    @Async
    @SneakyThrows
    public void test2(){
        Thread.sleep(1000L);
    }
}
