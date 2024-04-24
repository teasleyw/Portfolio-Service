package com.FrostMilano.Portfolio.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String job;
    private Boolean topStatus;
    private String email;
    private byte[] resume;
    private byte[] profilePicture;
    private String description;
    private String workPreference;
    private String experience;
    private String attainment;
    private String location;
    private String company;
    private String quota;
    private String compRange;
    private String industry;
}
