package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.AccountModel;
import com.example.demo.model.AccountNickName;
import com.example.demo.model.Index;
import com.example.demo.model.DB.Account;
import com.example.demo.model.DB.PostingSystem;
import com.example.demo.model.DB.Taskform;
import com.example.demo.service.MemberService;
import com.example.demo.service.PersonalTaskService;
import com.example.demo.service.PostingSystemService;
import com.example.demo.service.TaskformService;

@Configuration
@EnableScheduling
@RestController
@RequestMapping("/Task")
public class TaskController {

	@Autowired
	private TaskformService _taskformService;

	@Autowired
	private MemberService _memberService;

	@Autowired
	private PostingSystemService _postingSystemService;

	@Autowired
	private PersonalTaskService _personalTaskService;

	public TaskController(TaskformService taskformService, PersonalTaskService personalTaskService) {
		_taskformService = taskformService;
		_personalTaskService = personalTaskService;
	}

	// 固定時間做事 [秒] [分] [小時] [日] [月] [年]
	@Scheduled(cron = "0 0 0 * * *")
	@RequestMapping("task2")
	public List<Taskform> selectQuestid() {
		return _personalTaskService.queryByQuestid();
	}

	// 搜尋任務
	@PostMapping("/task1")
	public List<Taskform> selectContent(String Content) {
		return _taskformService.selectContent("%" + Content + "%");
	}

	// 搜尋用戶
	@PostMapping("/findByNickname")
	public List<AccountNickName> findByNickname(@RequestBody Account account) {
		List<AccountNickName> accountNickName = _memberService.findByNickNameLike("%" + account.getNickName() + "%");
		return accountNickName ;
	}

	// 搜尋貼文
	@PostMapping("/findByType")
	public ResponseEntity<List<Index>> findByType(@RequestBody PostingSystem postingSystem) {
		
		return _postingSystemService.findByTaskType(postingSystem.getTaskType(), postingSystem.getMemberEmail());
	}

	@PostMapping("/finishquest")
	public String finishquest(String email, String trueorfalse) {
		_taskformService.finishquest(email, trueorfalse);
		return "nice";
	}

	@Scheduled(cron = "0 0 0 * * *")
	@PostMapping("/reset")
	public String sheduled() {
		_taskformService.resetTable();
		return "reset!!!!";
	}

	@GetMapping("/Achievement")
	public List<Taskform> Achievement(@CookieValue(value = "userName", defaultValue = "Atta") String email) {
		System.out.println(_taskformService.Achievement(email));
		return _taskformService.Achievement(email);

	}

}
