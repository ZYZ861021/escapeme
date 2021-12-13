package com.example.demo.model;

import lombok.Data;

@Data
public class BuyCommodityReqDTO {

	private Integer id;

	private String address;

	private String orderName;

	private String phone;

	private String orderId;

	private Integer shoppingId;

	public BuyCommodityReqDTO(Integer id, String address, String orderName, String phone, String orderId,
			Integer shoppingId) {

		this.id = id;
		this.address = address;
		this.orderName = orderName;
		this.phone = phone;
		this.orderId = orderId;
		this.shoppingId = shoppingId;
	}

	public BuyCommodityReqDTO() {

	}

	public Integer getId() {
		return id;
	}

	public String getAddress() {
		return address;
	}

	public String getOrderName() {
		return orderName;
	}

	public String getPhone() {
		return phone;
	}

	public String getOrderId() {
		return orderId;
	}

	public Integer getShoppingId() {
		return shoppingId;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setShoppingId(Integer shoppingId) {
		this.shoppingId = shoppingId;
	}

	@Override
	public String toString() {
		return "BuyCommodityReqDTO [id=" + id + ", address=" + address + ", orderName=" + orderName + ", phone=" + phone
				+ ", orderId=" + orderId + ", shoppingId=" + shoppingId + "]";
	}

}
