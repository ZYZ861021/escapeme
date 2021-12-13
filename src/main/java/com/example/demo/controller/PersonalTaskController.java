package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.MissionView;
import com.example.demo.model.ResultViewModel;
import com.example.demo.model.DB.PersonalTask;
import com.example.demo.model.DB.PostingSystem;
import com.example.demo.model.DB.Taskform;
import com.example.demo.service.PersonalTaskService;

@RestController
@RequestMapping("/api/rand")
public class PersonalTaskController {
	
	@Autowired
	private PersonalTaskService _personalTaskService;

	@PostMapping("/update")
	public void update(@RequestBody PersonalTask personalTask) {
		_personalTaskService.update(personalTask);
	}
	@GetMapping("/look")
	public MissionView look(@CookieValue(value = "userName", defaultValue = "Atta")String email) {
		return _personalTaskService.questIn(email);
	}
	@PostMapping("/delete")
	public void delete() {
		_personalTaskService.delete();
	}
	@PostMapping("/random")
	public Taskform random(Taskform quest ,String email) {
		return _personalTaskService.random(email);
	}
	@PostMapping("/true")
	public ResultViewModel truebuttom(@CookieValue(value = "userName", defaultValue = "Atta")String email,@RequestBody PostingSystem postingSystem) {
		
		return 	_personalTaskService.finishquest(email,postingSystem);
	}
	@PostMapping("/ExtractionTask")
	public MissionView falsebuttom(@CookieValue(value = "userName", defaultValue = "Atta")String email) {
		return _personalTaskService.falsequest(email);
	}
	
	@PostMapping("/over")
	@Scheduled(cron = "0 0 0 * * *")
	public String over() {
		_personalTaskService.delete();
		return "clear";
	}
	
	
}