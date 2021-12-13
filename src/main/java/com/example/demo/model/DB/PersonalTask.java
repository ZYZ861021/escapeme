package com.example.demo.model.DB;

import java.text.SimpleDateFormat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;


@Entity
@Table(name = "personal_task")
@EntityListeners(AuditingEntityListener.class)
public class PersonalTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "email")
	private String email;

	@Column(name = "true_quest")
	private String true_quest;

	@Column(name = "false_quest")
	private String false_quest;

	@Column(name = "now_quest")
	private Integer now_quest;

	@CreatedDate()
	@Column(name = "cr_date")
	private String cr_date;

	@CreatedDate()
	@Column(name = "up_date")
	private String up_date;

	@Column(name = "frequency")
	private Integer frequency;

	public PersonalTask() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PersonalTask(Integer id, String email, String true_quest, String false_quest, Integer now_quest,
			String cr_date, String up_date, Integer frequency) {
		super();
		this.id = id;
		this.email = email;
		this.true_quest = true_quest;
		this.false_quest = false_quest;
		this.now_quest = now_quest;
		this.cr_date = cr_date;
		this.up_date = up_date;
		this.frequency = frequency;
	}

	@Override
	public String toString() {
		return "PersonalTask [id=" + id + ", email=" + email + ", true_quest=" + true_quest + ", false_quest="
				+ false_quest + ", now_quest=" + now_quest + ", cr_date=" + cr_date + ", up_date=" + up_date
				+ ", frequency=" + frequency + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTrue_quest() {
		return true_quest;
	}

	public void setTrue_quest(String true_quest) {
		this.true_quest = true_quest;
	}

	public String getFalse_quest() {
		return false_quest;
	}

	public void setFalse_quest(String false_quest) {
		this.false_quest = false_quest;
	}

	public Integer getNow_quest() {
		return now_quest;
	}

	public void setNow_quest(Integer now_quest) {
		this.now_quest = now_quest;
	}

	public String getCr_date() {
		return cr_date;
	}

	public void setCr_date(String cr_date) {
		this.cr_date = cr_date;
	}

	public String getUp_date() {
		return up_date;
	}

	public void setUp_date(String up_date) {
		this.up_date = up_date;
	}

	public Integer getFrequency() {
		return frequency;
	}

	public void setFrequency(Integer frequency) {
		this.frequency = frequency;
	}

}