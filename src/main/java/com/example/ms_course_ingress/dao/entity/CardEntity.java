package com.example.ms_course_ingress.dao.entity;


import com.example.ms_course_ingress.model.enums.CardStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode(of = "id")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cards")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pan;

    @Enumerated(EnumType.STRING)
    private CardStatus status;

    private String cardholder;

}
