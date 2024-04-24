//package com.FrostMilano.Portfolio.Controllers;
//
//
//import com.FrostMilano.Portfolio.DTO.PoemRequest;
//import com.FrostMilano.Portfolio.Services.MailService;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class MailController {
//
//        MailService mailService = new MailService();
//    public MailController(MailService mailService) {
//        this.mailService = mailService;
//    }
//        @PostMapping ("/submitPoem")
//        public String sendSimpleMessage(@RequestBody PoemRequest poemData){
//            String firstName = poemData.getFirstName();
//            mailService.sendMail(poemData);
//            return firstName;
//        }
//    }
//
