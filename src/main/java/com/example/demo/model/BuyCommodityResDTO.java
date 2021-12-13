package com.example.demo.model;

import lombok.Data;

public class BuyCommodityResDTO {

	private Integer code;

	private String message;

	private Object data;

	private String nickName;

	private Integer star;

	public BuyCommodityResDTO() {

	}

	public BuyCommodityResDTO(Integer code, String message, Object data, String nickName, Integer star) {

		this.code = code;
		this.message = message;
		this.data = data;
		this.nickName = nickName;
		this.star = star;

	}

	public Integer getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public Object getData() {
		return data;
	}

	public String getNickName() {
		return nickName;
	}

	public Integer getStar() {
		return star;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setStar(Integer star) {
		this.star = star;
	}

	@Override
	public String toString() {
		return "BuyCommodityResDTO [code=" + code + ", message=" + message + ", data=" + data + ", nickName=" + nickName
				+ ", star=" + star + "]";
	}
}
