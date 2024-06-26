package com.FrostMilano.Portfolio.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {


    private Long id;
    private String firstName;
    private String lastName;
    private String role;
    private String job;
    private Boolean topStatus;
    private String experience;
    private String description;
    private String email;
    private String login;
    private String token;
    private String attainment;
    private String location;
    private String quota;
    private String compRange;
    private String industry;
    private String company;
}
