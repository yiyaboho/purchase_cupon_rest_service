package com.purchase.coupon.repository.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	
	@ManyToMany
	@JoinTable(name = "user_item", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
	private Set<Item> items;

	public Set<Item> getItems() {
		return items;
	}

}
