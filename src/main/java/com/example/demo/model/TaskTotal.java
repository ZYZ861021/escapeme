package com.example.demo.model;

import com.example.demo.model.DB.PersonalTask;
import com.example.demo.model.DB.Taskform;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
public class TaskTotal {
	
	private Taskform taskform;
	
	private PersonalTask personalTask;
}
