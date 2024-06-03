package com.FrostMilano.Portfolio.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "Q_and_A_table")
public class QandA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "question", nullable = false)
    @Size(max = 100)
    private String question;

    @Column(name = "answer", nullable = false)
    @Size(max = 2000)
    private String answer;


}
