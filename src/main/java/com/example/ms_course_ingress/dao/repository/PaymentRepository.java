package com.example.ms_course_ingress.dao.repository;

import com.example.ms_course_ingress.dao.entity.PaymentEntity;
import com.example.ms_course_ingress.model.enums.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    Optional<PaymentEntity> findByIdAndStatusNot(Long id, PaymentStatus status);
}
