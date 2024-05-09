package com.example.ms_course_ingress.service.concrete;


import org.springframework.stereotype.Service;

@Service
public class MathService {

    public Long sum (Long firstData, Long secondData){
        return firstData + secondData;
    }
}
