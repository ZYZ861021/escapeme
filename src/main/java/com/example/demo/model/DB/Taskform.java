package com.example.demo.model.DB;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "task")
@EntityListeners(AuditingEntityListener.class)
public class Taskform {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "mission_id")
	private Integer missionId;

	

	@Column(name = "title")
	private String Title;

	@Column(name = "quest")
	private String Quest;

	@Column(name = "star")
	private Integer Star;

	@Column(name = "type")
	private String Type;

	@Column(name = "mission_content")
	private String missionContent;

	public Taskform() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Taskform(Integer missionId, String title, String quest, Integer star, String type, String missionContent) {
		super();
		this.missionId = missionId;
		Title = title;
		Quest = quest;
		Star = star;
		Type = type;
		this.missionContent = missionContent;
	}

	@Override
	public String toString() {
		return "Taskform [missionId=" + missionId + ", Title=" + Title + ", Quest=" + Quest + ", Star=" + Star
				+ ", Type=" + Type + ", missionContent=" + missionContent + "]";
	}

	public Integer getMissionId() {
		return missionId;
	}

	public void setMissionId(Integer missionId) {
		this.missionId = missionId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getQuest() {
		return Quest;
	}

	public void setQuest(String quest) {
		Quest = quest;
	}

	public Integer getStar() {
		return Star;
	}

	public void setStar(Integer star) {
		Star = star;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getMissionContent() {
		return missionContent;
	}

	public void setMissionContent(String missionContent) {
		this.missionContent = missionContent;
	}
	
	

}
