package com.example.demo.model;

public class MemberAndStarRank {

	private Integer rank;	
	private Long memberId;
	private String nickName;
	private String userImgSrc;
	private Integer starCount;
	
	
	public MemberAndStarRank() {
		
	}

	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
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
	public String getUserImgSrc() {
		return userImgSrc;
	}
	public void setUserImgSrc(String userImgSrc) {
		this.userImgSrc = userImgSrc;
	}
	public Integer getStarCount() {
		return starCount;
	}
	public void setStarCount(Integer starCount) {
		this.starCount = starCount;
	}
	
	@Override
	public String toString() {
		return "MemberAndStarRank [rank=" + rank + ", memberId=" + memberId + ", nickName=" + nickName + ", userImgSrc="
				+ userImgSrc + ", starCount=" + starCount + "]";
	}
}
