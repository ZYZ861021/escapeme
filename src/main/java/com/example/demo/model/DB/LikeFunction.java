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
@Table(name = "islike")
@EntityListeners(AuditingEntityListener.class)
public class LikeFunction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "postid")
	private Integer postId;
	
	@Column(name = "islike")
	private Boolean isLike;
	
	@Column(name = "user")
	private String userEmail;
	
	public LikeFunction() {
		super();
	}
	
	public LikeFunction(Long id, Integer postId, Boolean isLike, String userEmail) {
		super();
		this.id = id;
		this.postId = postId;
		this.isLike = isLike;
		this.userEmail = userEmail;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public Boolean getIsLike() {
		return isLike;
	}

	public void setIsLike(Boolean isLike) {
		this.isLike = isLike;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	@Override
	public String toString() {
		return "LikeFunction [id=" + id + ", postId=" + postId + ", isLike=" + isLike + ", userEmail=" + userEmail
				+ "]";
	}


}
