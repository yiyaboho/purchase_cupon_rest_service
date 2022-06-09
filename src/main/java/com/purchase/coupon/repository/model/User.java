package com.purchase.coupon.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "User")
@Entity
@Getter
@Setter @NoArgsConstructor
public class User {

	@Id
	@Column(name="user_id", nullable = false, length = 100)
	private String userId;

	@Column(nullable = false, length = 100)
	private String login;

	public User(String userId) {
		super();
		this.userId = userId;
	}
	
	

}
