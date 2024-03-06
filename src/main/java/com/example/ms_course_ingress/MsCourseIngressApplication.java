package com.example.ms_course_ingress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class MsCourseIngressApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsCourseIngressApplication.class, args);
    }

}
