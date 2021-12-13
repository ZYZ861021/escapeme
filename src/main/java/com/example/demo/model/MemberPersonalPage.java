package com.example.demo.model;

public class MemberPersonalPage {

	private Long memberId;
	private String nickName;
	private String userImgSrc;
	private Integer missionCount;
	private Integer starCount;
	private Integer fansCount;
	private Integer followCount;
	private String introduction;
	private String email;
	private String phone;

	public MemberPersonalPage() {

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

	public Integer getMissionCount() {
		return missionCount;
	}

	public void setMissionCount(Integer missionCount) {
		this.missionCount = missionCount;
	}

	public Integer getStarCount() {
		return starCount;
	}

	public void setStarCount(Integer starCount) {
		this.starCount = starCount;
	}

	public Integer getFansCount() {
		return fansCount;
	}

	public void setFansCount(Integer fansCount) {
		this.fansCount = fansCount;
	}

	public Integer getFollowCount() {
		return followCount;
	}

	public void setFollowCount(Integer followCount) {
		this.followCount = followCount;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	@Override
	public String toString() {
		return "PersonalPage [memberId=" + memberId + ", nickName=" + nickName + ", userImgSrc=" + userImgSrc
				+ ", missionCount=" + missionCount + ", starCount=" + starCount + ", fansCount=" + fansCount
				+ ", followCount=" + followCount + ", introduction=" + introduction + ", email=" + email + ", phone="
				+ phone + "]";
	}

}
