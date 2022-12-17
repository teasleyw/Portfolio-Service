package com.FrostMilano.Portfolio.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PoemRequest {
    private String poem;
    private String email;
    private String firstName;
    private String lastName;
    private String poemTitle;
}
