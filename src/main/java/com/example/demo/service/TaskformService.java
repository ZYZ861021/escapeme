package com.example.demo.service;

import java.util.List;

import com.example.demo.model.DB.PersonalTask;
import com.example.demo.model.DB.Taskform;

public interface TaskformService {
	
	List<Taskform> selectContent(String Content);
	
	Taskform firstrandom(String email) ;

	PersonalTask save(PersonalTask personalTask);

	void finishquest(String email, String trueOrFalse);
	
	Integer findMaxId();
	
	PersonalTask findAllById(Integer id);

	void resetTable();
	

	List<Taskform> Achievement(String email);
}
