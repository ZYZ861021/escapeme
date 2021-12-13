package com.example.demo.model;

import com.example.demo.model.DB.Taskform;

public class MissionView {

	Integer missionLeft;
	Boolean isShow;
	Taskform mission;
	public MissionView() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MissionView(Integer missionLeft, Boolean isShow, Taskform mission) {
		super();
		this.missionLeft = missionLeft;
		this.isShow = isShow;
		this.mission = mission;
	}
	@Override
	public String toString() {
		return "MissionView [missionLeft=" + missionLeft + ", isShow=" + isShow + ", mission=" + mission + "]";
	}
	public Integer getMissionLeft() {
		return missionLeft;
	}
	public void setMissionLeft(Integer missionLeft) {
		this.missionLeft = missionLeft;
	}
	public Boolean getIsShow() {
		return isShow;
	}
	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}
	public Taskform getMission() {
		return mission;
	}
	public void setMission(Taskform mission) {
		this.mission = mission;
	}
	
	
	
	
}
