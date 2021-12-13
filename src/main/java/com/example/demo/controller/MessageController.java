package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Index;
import com.example.demo.model.DB.Message;
import com.example.demo.service.MessageService;

@RestController
@RequestMapping(value = "/Message")
public class MessageController {
	
	@Autowired
	private MessageService _messageService;

	@PostMapping("/message")
	public ResponseEntity<Index> Message(@RequestBody Message message,
			@CookieValue(value = "userName", defaultValue = "Atta") String userName) {
		System.out.println(message);
		
		if (message.getCommentContent() == null || message.getCommentContent() == "") {
			return null;
		}
		
		ResponseEntity<Index> messageAndContent = _messageService.SaveMessageAndReturnContent(message, userName);
		
		return messageAndContent;

	}
}
