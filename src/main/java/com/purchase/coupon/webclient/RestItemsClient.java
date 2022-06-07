package com.purchase.coupon.webclient;

import java.util.List;

import com.purchase.coupon.webclient.model.Item;

public interface RestItemsClient {
	
	public Item getItemInfo(String itemId);
	
	public List<Item> getItemsInfo(List<String> itemId);

}
