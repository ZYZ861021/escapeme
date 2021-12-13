package com.example.demo.model.DB;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
//MODEL

@Entity
@Table(name = "customer")
@EntityListeners(AuditingEntityListener.class)
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Email(message = "Email格式錯誤")
	@NotBlank(message = "Email不能為功")
	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "create_by")
	private String createBy;

	@Column(name = "create_dt")

	// 自動創建時間
	@CreatedDate()
	private Date createDt;

	@Column(name = "nickname")
	private String nickName;

	@Column(name = "phone")
	private String phone;

	@Column(name = "mailcheck")
	private String mail;

	@Column(name = "selfintro")
	private String selfIntro;

	@Column(name = "src")
	private String src;

	@Column(name = "historical_star")
	private Integer historicalStar;

	@Column(name = "remain_star")
	private Integer remainStar;

	public Account() {
	}

	public Account(Long id, String email, String password, String nickName, String phone, String createBy, String mail,
			String selfIntro, String src, Integer historicalStar, Integer remainStar) {
		this.id = id;
		this.email = email;
		this.password = password;
		this.nickName = nickName;
		this.phone = phone;
		this.createBy = createBy;
		this.mail = mail;
		this.selfIntro = selfIntro;
		this.src = src;
		this.historicalStar = historicalStar;
		this.remainStar = remainStar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getCreateDt() {
		return createDt;
	}

	public void setCreateDt(Date createDt) {
		this.createDt = createDt;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSelfIntro() {
		return selfIntro;
	}

	public void setSelfIntro(String selfIntro) {
		this.selfIntro = selfIntro;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public Integer getHistoricalStar() {
		return historicalStar;
	}

	public void setHistoricalStar(Integer historicalStar) {
		this.historicalStar = historicalStar;
	}

	public Integer getRemainStar() {
		return remainStar;
	}

	public void setRemainStar(Integer remainStar) {
		this.remainStar = remainStar;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", email=" + email + ", password=" + password + ", createBy=" + createBy
				+ ", createDt=" + createDt + ", nickName=" + nickName + ", phone=" + phone + ", mail=" + mail
				+ ", selfIntro=" + selfIntro + ", src=" + src + ", historicalStar=" + historicalStar + ", remainStar="
				+ remainStar + "]";
	}

}
