package com.example.demo.model;

import java.util.Date;
import java.util.List;

import com.example.demo.model.DB.Message;
import com.example.demo.model.DB.PostingSystem;
import com.fasterxml.jackson.annotation.JsonFormat;

public class Index {

	private Integer postId;
	private String nickName;
	private String userImgSrc;
	private Long memberId;
	private String type;
	private String title;
	private String quest;
	private String postImgSrc;
	private Integer likeCount;
	private Integer commentCount;
	private Boolean isLike;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone="GMT+8")
	private Date postTime;
	
	private String postContent;
	
	private List<Message> comments;

	public Index() {
		super();
	}

	public List<Message> getComments() {
		return comments;
	}

	public void setComments(List<Message> comments) {
		this.comments = comments;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getUserImgSrc() {
		return userImgSrc;
	}

	public void setUserImgSrc(String userImgSrc) {
		this.userImgSrc = userImgSrc;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getQuest() {
		return quest;
	}

	public void setQuest(String quest) {
		this.quest = quest;
	}

	public String getPostImgSrc() {
		return postImgSrc;
	}

	public void setPostImgSrc(String postImgSrc) {
		this.postImgSrc = postImgSrc;
	}

	public Integer getLikeCount() {
		return likeCount;
	}

	public void setLikeCount(Integer likeCount) {
		this.likeCount = likeCount;
	}

	public Integer getCommentCount() {
		return commentCount;
	}

	public void setCommentCount(Integer liketotal) {
		this.commentCount = liketotal;
	}

	public Boolean getIsLike() {
		return isLike;
	}

	public void setIsLike(Boolean isLike) {
		this.isLike = isLike;
	}

	public Date getPostTime() {
		return postTime;
	}

	public void setPostTime(Date date) {
		this.postTime = date;
	}

	public String getPostContent() {
		return postContent;
	}

	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}

	public  Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

}
