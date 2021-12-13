package com.example.demo.model.DB;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@Entity
@Table(name = "account_order")
@EntityListeners(AuditingEntityListener.class)
public class AccountOrder {

    //流水號
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

	//訂單編號(亂數)
    @Column(name = "order_id", nullable = false, length = 10)
    private String orderId;

    //商品id
    @Column(name = "shopping_id", nullable = false, length = 1000)
    private Integer shoppingId;

    @Column(name = "phone", nullable = false, length = 10)
    private String phone;

    @Column(name = "address", length = 100)
    private String address;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "order_name", length = 100)
    private String orderName;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    @CreatedDate()
    private Date commentTime;
    
	public AccountOrder() {
		
	}
    
    public AccountOrder(Integer id, String orderId, Integer shoppingId, String phone, String address, String email,
			String orderName, Date commentTime) {
		this.id = id;
		this.orderId = orderId;
		this.shoppingId = shoppingId;
		this.phone = phone;
		this.address = address;
		this.email = email;
		this.orderName = orderName;
		this.commentTime = commentTime;
	}
    
    public Integer getId() {
		return id;
	}

	public String getOrderId() {
		return orderId;
	}

	public Integer getShoppingId() {
		return shoppingId;
	}

	public String getPhone() {
		return phone;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public String getOrderName() {
		return orderName;
	}

	public Date getCommentTime() {
		return commentTime;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public void setShoppingId(Integer shoppingId) {
		this.shoppingId = shoppingId;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	
	@Override
	public String toString() {
		return "AccountOrder [id=" + id + ", orderId=" + orderId + ", shoppingId=" + shoppingId + ", phone=" + phone
				+ ", address=" + address + ", email=" + email + ", orderName=" + orderName + ", commentTime="
				+ commentTime + "]";
	}


}
