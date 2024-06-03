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
@Table(name = "work_history")
public class WorkHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "company", nullable = false)
    @Size(max = 100)
    private String company;

    @Column(name = "start_date", nullable = false)
    @Size(max = 1000)
    private String startDate;

    @Column(name = "end_date", nullable = false)
    @Size(max = 100)
    private String endDate;

    @Column(name = "job_title", nullable = false)
    @Size(max = 100)
    private String jobTitle;
}
