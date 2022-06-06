package com.purchase.coupon.webclient;

import com.purchase.coupon.webclient.model.Item;

public interface RestItemsClient {
	
	public  Item getItemPrice(String itemId);

}
