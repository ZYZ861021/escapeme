package com.example.demo.model.DB;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "message")
@EntityListeners(AuditingEntityListener.class)
public class Message {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "message_id")
	private Integer commentId;

	@Column(name = "post_id")
	private Integer post_Id;

	@Column(name = "nickname")
	private String nickName;
	
	@Column(name = "userimgsrc")
	private String userImgSrc;
	
	@Column(name = "commentcontent")
	private String commentContent;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone="GMT+8")
	@CreatedDate()
	private Date commentTime;
	
	@Column(name = "memberId")
	private Long memberId;
	
	public Long getMemberId() {
		return memberId;
	}


	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}


	public Message() {
		
	}
	

	public Message(Integer commentId, Integer post_Id, String nickName, String userImgSrc, String commentContent,
			Date commentTime,Long memberId) {
		super();
		this.commentId = commentId;
		this.post_Id = post_Id;
		this.nickName = nickName;
		this.userImgSrc = userImgSrc;
		this.commentContent = commentContent;
		this.commentTime = commentTime;
		this.memberId = memberId;
	}
	
	public Integer getCommentId() {
		return commentId;
	}


	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}


	public Integer getPostId() {
		return post_Id;
	}


	public void setPostId(Integer post_Id) {
		this.post_Id = post_Id;
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


	public String getCommentContent() {
		return commentContent;
	}


	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}


	public Date getCommentTime() {
		return commentTime;
	}


	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}

	@Override
	public String toString() {
		return "Message [commentId=" + commentId + ", post_Id=" + post_Id + ", nickName=" + nickName + ", userImgSrc="
				+ userImgSrc + ", commentContent=" + commentContent + ", commentTime=" + commentTime + ", memberId" + memberId + "]";
	}





	
}
