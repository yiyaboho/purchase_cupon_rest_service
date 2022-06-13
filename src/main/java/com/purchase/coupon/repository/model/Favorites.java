package com.purchase.coupon.repository.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "Favorite")
@Entity
@Getter
@Setter @NoArgsConstructor @AllArgsConstructor
public class Favorites {

	@Id
	@Column(name = "item_id", nullable = false, length = 100)
	private String itemId;

	@Column(name = "likes")
	Integer quantity;

}