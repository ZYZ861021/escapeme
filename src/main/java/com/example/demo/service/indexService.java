package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Index;
import com.example.demo.model.PostNumber;

public interface indexService {
	
	public ResponseEntity<List<Index>> HomePost(String userName,Integer postNumber);
	
	
	
}
