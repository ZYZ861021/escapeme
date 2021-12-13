package com.example.demo.model;

public class AccountModel {

	
	private Long id;
	private String nickName;
	private String src;
	private Integer remain_star;
	
	public AccountModel() {
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Integer getRemain_star() {
		return remain_star;
	}

	public void setRemain_star(Integer remain_star) {
		this.remain_star = remain_star;
	}
	
	@Override
	public String toString() {
		return "AccountModel [id=" + id + ", nickName=" + nickName + ", src=" + src + ", remain_star=" + remain_star
				+ "]";
	}

}
