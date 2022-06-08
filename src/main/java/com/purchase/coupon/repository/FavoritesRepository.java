package com.purchase.coupon.repository;

import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public interface FavoritesRepository {
	
	public Map<String, Integer> getTopFavorites(int top);

}
