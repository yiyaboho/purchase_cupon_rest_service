package com.purchase.coupon.service;

import org.springframework.stereotype.Component;

import com.purchase.coupon.model.CouponItem;
import com.purchase.coupon.repository.model.Item;
import com.purchase.coupon.repository.model.User;

@Component
public interface FavoritesService {
	
	public Item setFavorite(String userId, String itemId);
	
	public User deleteFavorite(String userId, String itemId);
	
	public CouponItem[] getTopFavorites(int top);

}
