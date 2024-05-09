package com.example.ms_course_ingress.dao.entity;

import com.example.ms_course_ingress.model.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.EnumType.STRING;

//import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
@Builder
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(STRING)
    private PaymentStatus status;

    private Integer amount;

    private String description;

}
