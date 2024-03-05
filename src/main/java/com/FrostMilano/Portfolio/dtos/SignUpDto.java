package com.FrostMilano.Portfolio.dtos;

public record SignUpDto (String email,
                         String firstName,
                         String lastName,
                         String login,
                         char[] password) { }
