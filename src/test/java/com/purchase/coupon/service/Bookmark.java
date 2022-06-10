package com.purchase.coupon.service;

import com.purchase.coupon.repository.model.Favorites;

class Bookmark implements Favorites{
	
	public Bookmark(String item, Integer q) {
		this.itemId = item;
		this.quantity = q;
	}
	private String itemId;
	private Integer quantity;
	
	@Override
	public String getItemId() {
		return this.itemId;
	}
	@Override
	public Integer getQuantity() {
		return this.quantity;
	}
}
