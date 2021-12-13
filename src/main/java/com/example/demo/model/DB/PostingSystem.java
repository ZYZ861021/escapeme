package com.example.demo.model.DB;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "postingsystem")
@EntityListeners(AuditingEntityListener.class)
public class PostingSystem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "post_id")
	private Integer postId;

	@Column(name = "post_content")
	private String postContent;

	@Column(name = "post_image")
	private String postImage;

	@CreatedDate
	@Column(name = "create_dt")
	private Date createDt;

	@Column(name = "member_email")
	private String memberEmail;

	@Column(name = "mission_stars")
	private Integer missionStars;

	@Column(name = "quest")
	private String quest;

	@Column(name = "count_messages")
	private Integer countMessages;

	@Column(name = "task_type")
	private String taskType;

	@Column(name = "mission_id")
	private Integer missionId;

	public PostingSystem() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PostingSystem(Integer postId, String postContent, String postImage, Date createDt, String memberEmail,
			Integer missionStars, String quest, Integer countMessages, String taskType, Integer missionId) {
		super();
		this.postId = postId;
		this.postContent = postContent;
		this.postImage = postImage;
		this.createDt = createDt;
		this.memberEmail = memberEmail;
		this.missionStars = missionStars;
		this.quest = quest;
		this.countMessages = countMessages;
		this.taskType = taskType;
		this.missionId = missionId;
	}

	@Override
	public String toString() {
		return "PostingSystem [postId=" + postId + ", postContent=" + postContent + ", postImage=" + postImage
				+ ", createDt=" + createDt + ", memberEmail=" + memberEmail + ", missionStars=" + missionStars
				+ ", quest=" + quest + ", countMessages=" + countMessages + ", taskType=" + taskType + ", missionId="
				+ missionId + "]";
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public String getPostImage() {
		return postImage;
	}

	public void setPostImage(String postImage) {
		this.postImage = postImage;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getMemberEmail() {
		return memberEmail;
	}

	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}

	public Integer getMissionStars() {
		return missionStars;
	}

	public void setMissionStars(Integer missionStars) {
		this.missionStars = missionStars;
	}

	public String getQuest() {
		return quest;
	}

	public void setQuest(String quest) {
		this.quest = quest;
	}

	public Integer getCountMessages() {
		return countMessages;
	}

	public void setCountMessages(Integer countMessages) {
		this.countMessages = countMessages;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public Integer getMissionId() {
		return missionId;
	}

	public void setMissionId(Integer missionId) {
		this.missionId = missionId;
	}

}
