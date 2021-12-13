package com.example.demo.model;

import lombok.Data;

@Data
public class PostNumber {
	
	private Integer postNumber;

	@Override
	public String toString() {
		return "PostNumber [postNumber=" + postNumber + "]";
	}

	public PostNumber() {
		super();
	}

	public Integer getPostNumber() {
		return postNumber;
	}

	public void setPostNumber(Integer postNumber) {
		this.postNumber = postNumber;
	}
	
}
