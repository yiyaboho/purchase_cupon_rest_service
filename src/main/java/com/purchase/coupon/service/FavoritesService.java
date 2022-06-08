package com.purchase.coupon.service;

import org.springframework.stereotype.Component;

import com.purchase.coupon.model.CouponItem;

@Component
public interface FavoritesService {
	
	public boolean setFavorite(String userId, String itemId);
	
	public CouponItem[] getTopFavorites(int top);

}
