package com.FrostMilano.Portfolio.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LinkedInUserInfoDto {
    private String given_name;
    private String family_name;
    private String email;
    private String picture;
}
