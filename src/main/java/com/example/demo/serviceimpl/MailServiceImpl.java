package com.example.demo.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import com.example.demo.service.MailService;


@Service
public class MailServiceImpl implements MailService{
	
	 @Autowired
	 private JavaMailSender _javaMailSender;
	   
	 public void sendMail(String to, String subject, String text) {
	     SimpleMailMessage message = new SimpleMailMessage();

	     message.setFrom("johnny870218@gmail.com");
	     message.setTo(to);
	     message.setSubject(subject);
	     message.setText(text);
	     _javaMailSender.send(message);
	 }
}

