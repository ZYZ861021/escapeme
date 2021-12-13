package com.example.demo.model;

import com.example.demo.model.DB.PostingSystem;

public class total {

	private PostingSystem postingSystem;
	private PostNumber postNumber;
	public total() {
		super();
		// TODO Auto-generated constructor stub
	}
	public total(PostingSystem postingSystem, PostNumber postNumber) {
		super();
		this.postingSystem = postingSystem;
		this.postNumber = postNumber;
	}
	@Override
	public String toString() {
		return "total [postingSystem=" + postingSystem + ", postNumber=" + postNumber + "]";
	}
	public PostingSystem getPostingSystem() {
		return postingSystem;
	}
	public PostNumber getPostNumber() {
		return postNumber;
	}
	public void setPostingSystem(PostingSystem postingSystem) {
		this.postingSystem = postingSystem;
	}
	public void setPostNumber(PostNumber postNumber) {
		this.postNumber = postNumber;
	}
	
	
	
	
}
