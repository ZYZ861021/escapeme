package com.example.demo.model;

public class CountTaskType {
	
	private String nickname;
	private String src;
	private Long member_id;
	private Integer mission_type_1;
	private Integer mission_type_2;
	private Integer mission_type_3;
	private Integer mission_type_4;
	private Integer mission_type_5;
	private Integer mission_type_6;
	private Integer mission_type_7;
	private Integer mission_type_8;
	private Integer mission_type_9;
	private Integer mission_type_10;
	private Integer mission_type_11;
	public CountTaskType() {
		
	}
	public CountTaskType(String nickname, String src, Long member_id, Integer mission_type_1, Integer mission_type_2,
			Integer mission_type_3, Integer mission_type_4, Integer mission_type_5, Integer mission_type_6,
			Integer mission_type_7, Integer mission_type_8, Integer mission_type_9, Integer mission_type_10,
			Integer mission_type_11) {
		
		this.nickname = nickname;
		this.src = src;
		this.member_id = member_id;
		this.mission_type_1 = mission_type_1;
		this.mission_type_2 = mission_type_2;
		this.mission_type_3 = mission_type_3;
		this.mission_type_4 = mission_type_4;
		this.mission_type_5 = mission_type_5;
		this.mission_type_6 = mission_type_6;
		this.mission_type_7 = mission_type_7;
		this.mission_type_8 = mission_type_8;
		this.mission_type_9 = mission_type_9;
		this.mission_type_10 = mission_type_10;
		this.mission_type_11 = mission_type_11;
	}
	
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getsrc() {
		return src;
	}
	public void setsrc(String src) {
		this.src = src;
	}
	public Long getMember_id() {
		return member_id;
	}
	public void setMember_id(Long member_id) {
		this.member_id = member_id;
	}
	public Integer getMission_type_1() {
		return mission_type_1;
	}
	public void setMission_type_1(Integer mission_type_1) {
		this.mission_type_1 = mission_type_1;
	}
	public Integer getMission_type_2() {
		return mission_type_2;
	}
	public void setMission_type_2(Integer mission_type_2) {
		this.mission_type_2 = mission_type_2;
	}
	public Integer getMission_type_3() {
		return mission_type_3;
	}
	public void setMission_type_3(Integer mission_type_3) {
		this.mission_type_3 = mission_type_3;
	}
	public Integer getMission_type_4() {
		return mission_type_4;
	}
	public void setMission_type_4(Integer mission_type_4) {
		this.mission_type_4 = mission_type_4;
	}
	public Integer getMission_type_5() {
		return mission_type_5;
	}
	public void setMission_type_5(Integer mission_type_5) {
		this.mission_type_5 = mission_type_5;
	}
	public Integer getMission_type_6() {
		return mission_type_6;
	}
	public void setMission_type_6(Integer mission_type_6) {
		this.mission_type_6 = mission_type_6;
	}
	public Integer getMission_type_7() {
		return mission_type_7;
	}
	public void setMission_type_7(Integer mission_type_7) {
		this.mission_type_7 = mission_type_7;
	}
	public Integer getMission_type_8() {
		return mission_type_8;
	}
	public void setMission_type_8(Integer mission_type_8) {
		this.mission_type_8 = mission_type_8;
	}
	public Integer getMission_type_9() {
		return mission_type_9;
	}
	public void setMission_type_9(Integer mission_type_9) {
		this.mission_type_9 = mission_type_9;
	}
	public Integer getMission_type_10() {
		return mission_type_10;
	}
	public void setMission_type_10(Integer mission_type_10) {
		this.mission_type_10 = mission_type_10;
	}
	public Integer getMission_type_11() {
		return mission_type_11;
	}
	public void setMission_type_11(Integer mission_type_11) {
		this.mission_type_11 = mission_type_11;
	}
	
	@Override
	public String toString() {
		return "CountTaskType [nickname=" + nickname + ", src=" + src + ", member_id=" + member_id + ", mission_type_1="
				+ mission_type_1 + ", mission_type_2=" + mission_type_2 + ", mission_type_3=" + mission_type_3
				+ ", mission_type_4=" + mission_type_4 + ", mission_type_5=" + mission_type_5 + ", mission_type_6="
				+ mission_type_6 + ", mission_type_7=" + mission_type_7 + ", mission_type_8=" + mission_type_8
				+ ", mission_type_9=" + mission_type_9 + ", mission_type_10=" + mission_type_10 + ", mission_type_11="
				+ mission_type_11 + "]";
	}
}
