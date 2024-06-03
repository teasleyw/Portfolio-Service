package com.FrostMilano.Portfolio.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QandADto {
    private Long id;
    private Long userId;
    private String question;
    private String answer;
}
