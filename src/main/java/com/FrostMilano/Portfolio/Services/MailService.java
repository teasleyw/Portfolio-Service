//package com.FrostMilano.Portfolio.Services;
//
//import com.FrostMilano.Portfolio.DTO.PoemRequest;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//import org.springframework.stereotype.Service;
//
//import java.util.Properties;
//
//@Service
//public class MailService {
//    @Value("${mail.host}")
//    private String host;
//    @Value("${mail.port}")
//    private Integer port;
//    @Value("${mail.username}")
//    private String userName;
//    @Value("${mail.password}")
//    private String password;
//
//    private JavaMailSender emailSender;
//
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//        mailSender.setHost(host);
//        mailSender.setPort(587);
//
//        mailSender.setUsername(userName);
//        mailSender.setPassword(password);
//
//        Properties props = mailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        return mailSender;
//    }
//
//    public void sendMail(PoemRequest data){
//        SimpleMailMessage message = new SimpleMailMessage();
//        emailSender = getJavaMailSender();
//        message.setFrom("FrostMilano42@gmail.com");
//        message.setTo("FrostMilano42@gmail.com");
//        message.setSubject(data.getPoemTitle());
//        message.setText("Poem Author: " + data.getFirstName() + " " + data.getLastName() +"\n\n" +
//                "Poem Content: \n\n" + data.getPoem());
//        emailSender.send(message);
//    }
//}
