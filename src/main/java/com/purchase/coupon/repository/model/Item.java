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

@Table(name = "Item")
@Entity
@Getter
@Setter @NoArgsConstructor
public class Item {

	@Id
	@Column(name = "item_id", nullable = false, length = 100)
	private String itemId;

	@Column(nullable = false, length = 100)
	private String description;

	@ManyToMany
	@JoinTable(name = "user_item", joinColumns = @JoinColumn(name = "item_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
	private Set<User> users;

	public Set<User> getUsers() {
		return users;
	}

}
