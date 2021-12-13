package com.example.demo.service;

import java.util.List;

import com.example.demo.model.MissionView;
import com.example.demo.model.ResultViewModel;
import com.example.demo.model.DB.PersonalTask;
import com.example.demo.model.DB.PostingSystem;
import com.example.demo.model.DB.Taskform;

public interface PersonalTaskService {
//		 void add(PersonalTask personalTask) ;
	void update(PersonalTask personalTask);

	MissionView questIn(String email);

	void delete();

	Taskform random(String email);

	ResultViewModel finishquest(String email,PostingSystem postingSystem);

	MissionView falsequest(String email);
	
	List<Taskform> findAll();
	
	List<Taskform> queryByQuestid();
}
