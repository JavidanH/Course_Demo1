package com.example.ms_course_ingress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableCaching
public class MsCourseIngressApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCourseIngressApplication.class, args);
    }

}
