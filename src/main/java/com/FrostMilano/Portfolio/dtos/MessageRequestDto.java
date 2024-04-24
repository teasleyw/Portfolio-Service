package com.FrostMilano.Portfolio.dtos;

public record MessageRequestDto (String sender,
                                 String receiver,
                                 String content,
                                 String type) { }
