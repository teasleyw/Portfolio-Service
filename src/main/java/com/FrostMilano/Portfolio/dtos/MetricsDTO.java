package com.FrostMilano.Portfolio.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MetricsDTO {
    private Long id;
    private Long userId;
    private String year;
    private String salesCycle;
    private String targetVerticals;
    private String buyerPersona;
    private String avgDeal;
    private String attainment;
    private String quota;
}
