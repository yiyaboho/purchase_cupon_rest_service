package com.purchase.coupon.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.purchase.coupon.model.ItemsToBy;

@Component
public interface CouponService {

	public ItemsToBy getItemsToBy(List<String> items, double amount) throws Exception;
}
