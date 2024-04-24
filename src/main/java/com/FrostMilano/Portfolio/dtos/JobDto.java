package com.FrostMilano.Portfolio.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobDto  {
    private Long id;
    private String title;
    private String description;
    private String workType;
    private String location;
    private String experience;
    private String company;
}