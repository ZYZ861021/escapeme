package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Index;
import com.example.demo.model.DB.Message;

public interface MessageService {
	
	ResponseEntity<Index> SaveMessageAndReturnContent(Message message,String userName);

}
