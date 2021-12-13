package com.example.demo.model;

public class AccountNickName {
	
	private Long memberId;

	private String nickName;
	
	private String src;

	public AccountNickName() {
	
	}
	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	
	@Override
	public String toString() {
		return "AccountNickName [memberId=" + memberId + ", nickName=" + nickName + ", src" + src + "]";
	}

}
