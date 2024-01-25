package com.example.ms_course_ingress.dao.repository;

import com.example.ms_course_ingress.dao.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
}
