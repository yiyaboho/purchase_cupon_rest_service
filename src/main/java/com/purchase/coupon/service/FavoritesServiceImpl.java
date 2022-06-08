package com.purchase.coupon.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purchase.coupon.model.CouponItem;
import com.purchase.coupon.repository.FavoritesRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FavoritesServiceImpl implements FavoritesService {

	@Autowired
	FavoritesRepository favoritesRepository;
	
	@Override
	public boolean setFavorite(String userId, String itemId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CouponItem[] getTopFavorites(int top) {
		CouponItem[] response = new CouponItem[top];
		Map<String, Integer> favorites = favoritesRepository.getTopFavorites(top);
		int i = 0;
		
		for (Map.Entry<String, Integer> entry : favorites.entrySet()) {
			response[i++] = new CouponItem(entry.getKey(), entry.getValue());			
		}

		return response;
	}

}
