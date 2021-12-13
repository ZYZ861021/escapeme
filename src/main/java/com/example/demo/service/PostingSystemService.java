package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.CountTaskType;
import com.example.demo.model.Index;
import com.example.demo.model.PostNumber;
import com.example.demo.model.DB.PostingSystem;

public interface PostingSystemService {
	
	PostingSystem save(PostingSystem postingSystem);

	void update();
	
	PostingSystem findAllBypostId(Integer postId);

	// 搜尋主題任務tasktype跳出相關主題貼文
	ResponseEntity<List<Index>> findByTaskType(String taskType,String userName);
	
	ResponseEntity<List<Index>> SelectPost(String userName);
	
	CountTaskType countTaskType(Long member_id);

}
