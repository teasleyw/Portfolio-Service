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
@Table(name = "job_table")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    @Size(max = 100)
    private String title;

    @Column(name = "description", nullable = false)
    @Size(max = 1000)
    private String description;

    @Column(name = "work_type", nullable = false)
    @Size(max = 100)
    private String workType;

    @Column(name = "location", nullable = false)
    @Size(max = 100)
    private String location;

    @Column(name = "experience", nullable = false)
    @Size(max = 100)
    private String experience;

    @Column(name = "company", nullable = false)
    @Size(max = 100)
    private String company;
}
