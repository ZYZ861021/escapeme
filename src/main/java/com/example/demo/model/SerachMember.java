package com.example.demo.model;

public class SerachMember {
	

	private String memberId;

	private String src;
	
	private String nickName;
	
	public SerachMember() {
	}
	
	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Override
	public String toString() {
		return "SerachMember [memberId=" + memberId + ", src=" + src + ", nickName=" + nickName + "]";
	}

}
