package com.example.demo.serviceimpl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.DB.PersonalTask;
import com.example.demo.model.DB.Taskform;
import com.example.demo.repository.PersonalTaskRepository;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskformService;

@Service
public class TaskformServiceImpl implements TaskformService {
	
	@Autowired
	private TaskRepository _taskRepository;
	
	@Autowired
	private PersonalTaskRepository _personalTaskRepository;

	public TaskformServiceImpl(TaskRepository taskRepository) {
		_taskRepository = taskRepository;
	}

	public List<Taskform> selectContent(String Content) {
		return _taskRepository.queryByContent(Content);
	}

	public PersonalTask save(PersonalTask personalTask) {
		return _personalTaskRepository.save(personalTask);
	}

	public Taskform firstrandom(String email) {

			return null;
	}
	
	public void finishquest(String email, String trueOrFalse) {

	}

	public Integer findMaxId() {
		return _personalTaskRepository.findMaxId();
	}

	
	public PersonalTask findAllById(Integer id) {
		return _personalTaskRepository.findAllById(id);
	}

	
	public void resetTable() {
		for (int y = 1; y < findMaxId() + 1; y++) {
			PersonalTask personalTask = findAllById(y);
		
			if (personalTask.getNow_quest() == 0) {

				personalTask.setFrequency(3);
				_personalTaskRepository.flush();
			} else {

				personalTask.setFrequency(3);
				_personalTaskRepository.flush();
			}

		}
	}

	public List<Taskform> Achievement(String email) {
		List<Integer> total = new ArrayList<Integer>();
		List<Integer> personal = new ArrayList<Integer>();
		List<Taskform> returnview = new ArrayList<Taskform>();
		total = _taskRepository.lifequestid();
		personal = _personalTaskRepository.Querypersonallife(email);
		Collections.sort(personal);
		for (Integer i : personal) {
			if(total.add(i) == true) {
				total.remove(i);
				total.remove(i);
			}
		}
		for(Integer i : total) { 
			returnview.add(_taskRepository.findByQuest(i));
		}
		return returnview;
	}
	

}
