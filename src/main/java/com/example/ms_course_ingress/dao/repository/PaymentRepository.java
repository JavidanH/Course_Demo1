package com.example.ms_course_ingress.dao.repository;

import com.example.ms_course_ingress.dao.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {


}
