package com.FrostMilano.Portfolio.Services;

import com.FrostMilano.Portfolio.dtos.PoemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendPoemEmail(PoemDto poemDto) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("frostmilano42@gmail.com");
        message.setTo("frostmilano42@gmail.com");
        message.setSubject("New Poem Submission");
        message.setText(formatPoem(poemDto));
        emailSender.send(message);
    }

    private String formatPoem(PoemDto poemDto) {
        return "Title: " + poemDto.getTitle() + "\n\n" +
                "Author: " + poemDto.getAuthor() + "\n\n" +
                "Date: " + poemDto.getDate() + "\n\n" +
                "Poem:\n" + poemDto.getPoem();
    }
}