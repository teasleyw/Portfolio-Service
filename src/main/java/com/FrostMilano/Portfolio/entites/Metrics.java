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
@Table(name = "user_metrics")
public class Metrics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Size(max = 100)
    @Column(nullable = false)
    private String year;

    @Size(max = 100)
    private String salesCycle;

    @Size(max = 100)
    private String targetVerticals;

    @Size(max = 100)
    private String buyerPersona;

    @Size(max = 100)
    private String avgDeal;

    @Size(max = 100)
    private String attainment;

    @Size(max = 100)
    private String quota;






}
