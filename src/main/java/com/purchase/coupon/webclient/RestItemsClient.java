package com.purchase.coupon.webclient;

import java.util.List;

import com.purchase.coupon.webclient.model.Item;

public interface RestItemsClient {
	
	
	public List<Item> getListItemsInfo(List<String> itemId);

}
