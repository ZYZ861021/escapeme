package com.example.demo.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.imageio.stream.MemoryCacheImageInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.MissionView;
import com.example.demo.model.ResultViewModel;
import com.example.demo.model.DB.Account;
import com.example.demo.model.DB.PersonalTask;
import com.example.demo.model.DB.PostingSystem;
import com.example.demo.model.DB.Taskform;
import com.example.demo.repository.PersonalTaskRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.MemberService;
import com.example.demo.service.PersonalTaskService;
import com.example.demo.service.PostingSystemService;

@Service
public class PersonalTaskServiceImpl implements PersonalTaskService {
	
	@Autowired
	private MemberService _memberService;
	
	@Autowired
	private PostingSystemService _postingSystemService;

	@Autowired
	private TaskRepository _questRepository;

	@Autowired
	private PersonalTaskRepository _personalTaskRepository;

	public void update(PersonalTask personalTask) {
		PersonalTask newperPersonalTask = _personalTaskRepository.getByEmail(personalTask.getEmail());
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		String nowdate = sdFormat.format(date);
		newperPersonalTask.setUp_date(nowdate);
		_personalTaskRepository.flush();
	}

	public MissionView questIn(String email) {
		PersonalTask personalTask = _personalTaskRepository.Queryemail(email);
		MissionView missionView = new MissionView();
		if(personalTask != null) {
			if (_personalTaskRepository.todayemail(email) == null) {
				PersonalTask personalTask1 = new PersonalTask();
				personalTask1.setEmail(email);
				personalTask1.setNow_quest(personalTask.getNow_quest());
				personalTask1.setFrequency(3);
				personalTask1.setTrue_quest("0");
				personalTask1.setFalse_quest("0");
				_personalTaskRepository.save(personalTask1);
				if(personalTask.getNow_quest() == 0) {
					missionView.setIsShow(false);
					missionView.setMission(_questRepository.findByQuest(100));
				}else {
					missionView.setIsShow(true);
					missionView.setMission(_questRepository.findByQuest(personalTask.getNow_quest()));
				}
				missionView.setMissionLeft(3);
			}else {
				PersonalTask todayperPersonalTask = _personalTaskRepository.todayemail(email);
				//以前有登過 今天也登過
				if(todayperPersonalTask.getNow_quest() == 0) {
					missionView.setIsShow(false);
					missionView.setMission(_questRepository.findByQuest(100));
				}else {
					missionView.setIsShow(true);
					missionView.setMission(_questRepository.findByQuest(todayperPersonalTask.getNow_quest()));
				}
				missionView.setMissionLeft(todayperPersonalTask.getFrequency());
			}
		}else {
			PersonalTask personalTask1 = new PersonalTask();
			personalTask1.setEmail(email);
			personalTask1.setNow_quest(0);
			personalTask1.setFrequency(3);
			personalTask1.setTrue_quest("0");
			personalTask1.setFalse_quest("0");
			_personalTaskRepository.save(personalTask1);
			missionView.setIsShow(false);
			missionView.setMission(_questRepository.findByQuest(100));
			missionView.setMissionLeft(3);
		}
		return missionView;
	}

	public void delete() {
		List<Integer> MoreThan30DaysInformation = _personalTaskRepository.overday();
		for (Integer i : MoreThan30DaysInformation) {
			_personalTaskRepository.deleteById(i);
		}
	}

	public Taskform random(String email) {
		PersonalTask personalTask = _personalTaskRepository.Queryemail(email);
		List<Integer> RecenttTasks = new ArrayList<Integer>();
		List<Integer> AllTasks = new ArrayList<Integer>();
		List<Integer> falseandnow = new ArrayList<Integer>();
		for (String x : (personalTask.getFalse_quest() +","+ personalTask.getNow_quest()).split(",")) {
			falseandnow.add(Integer.valueOf(x));
		}
		for (String i : _personalTaskRepository.allTrue(email)) {
			for (String x : i.split(",")) {
				RecenttTasks.add(Integer.valueOf(x));
			}
		}
		RecenttTasks.addAll(falseandnow);
		Collections.sort(RecenttTasks);
		AllTasks = _questRepository.totalquestid();
		for (Integer i : RecenttTasks) {
			if (AllTasks.add(i) == true) {
				Integer indaex = AllTasks.indexOf(i);
				AllTasks.remove(indaex);
			}
		}
		
		Integer QuestId = AllTasks.get((int) (Math.random() * AllTasks.size()) + 1);
		personalTask.setNow_quest(QuestId);
		Taskform randomQuest = _questRepository.findByQuest(QuestId);
		_personalTaskRepository.flush();
		return randomQuest;
	}

	@Override
	public ResultViewModel finishquest(String email,PostingSystem postingSystem) {

		PersonalTask personalTask = _personalTaskRepository.Queryemail(email);
		ResultViewModel resultViewModel = new ResultViewModel();
		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
		Date date = new Date();
		Taskform task =_questRepository.findByQuest(postingSystem.getMissionId());
		String nowdate = sdFormat.format(date);
		personalTask.setUp_date(nowdate);
		if(postingSystem.getMissionId()<99) {
			personalTask.setTrue_quest(personalTask.getTrue_quest() + "," + postingSystem.getMissionId());
			personalTask.setNow_quest(0);			
		}
		_personalTaskRepository.flush();
		postingSystem.setMemberEmail(email);
		postingSystem.setTaskType(task.getType());
		postingSystem.setQuest(task.getQuest());
		postingSystem.setMissionStars(task.getStar());
		PostingSystem postingSystem1 = _postingSystemService.save(postingSystem);
		try {
			postingSystem1.setPostImage(
					_memberService.base64ToFile(null, postingSystem1.getPostImage()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		_postingSystemService.update();
		Account account = _memberService.findByEmail(postingSystem1.getMemberEmail());
		if(account.getRemainStar() == null) {
			account.setRemainStar(0);
			_memberService.update();
		}
		account.setRemainStar(account.getRemainStar() + task.getStar());
		_memberService.update();

		resultViewModel.setCode(200);
		return resultViewModel;
		}

	public MissionView falsequest(String email) {
		PersonalTask personalTask = _personalTaskRepository.Queryemail(email);
		MissionView missionView = new MissionView();
		if ((personalTask.getNow_quest() != 0 || personalTask.getFrequency() != 0)) {
			personalTask.setFalse_quest(personalTask.getFalse_quest() + "," + personalTask.getNow_quest());
			SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
			Date date = new Date();
			String nowdate = sdFormat.format(date);
			personalTask.setUp_date(nowdate);
			personalTask.setNow_quest(random(personalTask.getEmail()).getMissionId());
			while (personalTask.getFrequency() == 0) {
				personalTask.setFrequency(personalTask.getFrequency() + 1);
				personalTask.setNow_quest(0);
			}
			personalTask.setFrequency(personalTask.getFrequency() - 1);
			missionView.setMission(_questRepository.findByQuest(personalTask.getNow_quest()));
			missionView.setMissionLeft(personalTask.getFrequency());
			if ((personalTask.getNow_quest() == 0 && personalTask.getFrequency() == 0)) {
				missionView.setMission(_questRepository.findByQuest(100));
				missionView.setIsShow(false);
			} else {
				missionView.setIsShow(true);
			}
			_personalTaskRepository.flush();
		} else {
			missionView.setMission(_questRepository.findByQuest(100));
			missionView.setMissionLeft(0);
			missionView.setIsShow(false);
		}
		return missionView;
	}

	@Override
	public List<Taskform> findAll() {
		_questRepository.findAll();
		return null;
	}

	@Override
	public List<Taskform> queryByQuestid() {
		_questRepository.queryByQuestid();
		return null;
	}

}
