package com.purchase.coupon.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purchase.coupon.exception.BusinessException;
import com.purchase.coupon.exception.TechnicalException;
import com.purchase.coupon.model.ItemsToBy;
import com.purchase.coupon.webclient.RestItemsClient;
import com.purchase.coupon.webclient.model.Item;

@Service
public class CouponServiceImpl implements CouponService {

	@Autowired
	private RestItemsClient restItemsClient;
	
	@Override
	public ItemsToBy getItemsToBy(List<String> items, double amount) throws Exception{
		
		List<Item> priceItems = getPriceItems(items);
		List<String> couponItems = new ArrayList<>();
		
		priceItems = getRevertList(priceItems);
		double total = 0;
		
		for(Item item : priceItems) {
			double tmpTotal = total + item.getPrice();
			if(tmpTotal <= amount) {
				total = tmpTotal;
				couponItems.add(item.getId());
			}
		}
		
		if(couponItems == null || couponItems.isEmpty())
			throw new BusinessException("No se encontraron items que cumplan con el criterio");
		
		return new ItemsToBy(couponItems, total);
	}
	
	private List<Item> getRevertList(List<Item> items){
		Comparator<Item> comparator
	      = (item1, item2) -> Double.valueOf(item1.getPrice()).compareTo(Double.valueOf(item2.getPrice()));
	    
	      items.sort(comparator.reversed());
	      return items;
	}
	
	
	private List<Item> getPriceItems(List<String> items) {
		List<Item> priceItems = new ArrayList<>();
		for(String itemId : items) {
			Item price;
			try{
				price = restItemsClient.getItemPrice(itemId);
				priceItems.add(price);
			}catch (BusinessException ex) {
				System.out.print(ex.getMessage());
			}catch (TechnicalException ex) {
				throw ex;
			}
		}
		return priceItems;
	}

}
