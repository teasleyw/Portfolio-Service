package com.FrostMilano.Portfolio.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "user_table")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    @Size(max = 100)
    private String firstName;

    @Column(name = "email", nullable = false)
    @Size(max = 100)
    private String email;

    @Column(name = "role", nullable = false)
    @Size(max = 100)
    private String role;

    @Column(name = "Description")
    @Size(max = 2000)
    private String description;

    @Column(name = "experience")
    @Size(max = 100)
    private String experience;

    @Column(name = "work_preference")
    @Size(max = 100)
    private String workPreference;

    @Column(name = "top_candidate_status")
    private Boolean topStatus;

    @Column(name = "job", nullable = false)
    private String job;

    @Lob
    @Column(name = "resume")
    private byte[] resume;

    @Lob
    @Column(name = "profile_picture")
    private byte[] profilePicture;

    @Column(name = "last_name", nullable = false)
    @Size(max = 100)
    private String lastName;

    @Column(nullable = false)
    @Size(max = 100)
    private String login;

    @Column(nullable = false)
    @Size(max = 100)
    private String password;

    @Size(max = 100)
    private String attainment;

    @Size(max = 100)
    private String location;

    @Size(max = 100)
    private String company;

    @Size(max = 100)
    private String compRange;

    @Size(max = 100)
    private String industry;

    private String answerOne;

    private String answerTwo;

    @Size(max = 100)
    private String quota;
}
