package com.FrostMilano.Portfolio.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorkHistoryDto {
    private Long id;
    private Long userId;
    private String company;
    private String endDate;
    private String startDate;
    private String jobTitle;
}
