package com.purchase.coupon.repository.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter @Getter  @NoArgsConstructor
public class UserItem {
	
	@EmbeddedId
	UserItemKey id;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name="user_id")
	private User user;
	
	@ManyToOne
	@MapsId("itemId")
	@JoinColumn(name="item_id")
	private Item item;

}
