package com.purchase.coupon.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.purchase.coupon.controller.CuponController;
import com.purchase.coupon.exception.BusinessException;
import com.purchase.coupon.exception.TechnicalException;
import com.purchase.coupon.model.ItemsToBy;
import com.purchase.coupon.webclient.RestItemsClient;
import com.purchase.coupon.webclient.model.Item;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CouponServiceImpl implements CouponService {

	@Autowired
	private RestItemsClient restItemsClient;
	
	@Override
	public ItemsToBy getItemsToBy(List<String> items, double amount) throws BusinessException{
		log.info("ItemsToBy ");
		List<Item> priceItems = getPriceItems(items);
		List<String> couponItems = new ArrayList<>();
		
		double total = 0;
		
		for(Item item : sortList(priceItems)) {
			double tmpTotal = total + item.getPrice();
			if(tmpTotal <= amount) {
				total = tmpTotal;
				couponItems.add(item.getId());
			}
		}
		
		if(couponItems.isEmpty())
			throw new BusinessException("No se encontraron items que cumplan con el criterio");
		
		log.info("ItemsToBy finish");
		return new ItemsToBy(couponItems, total);
	}
	
	private List<Item> sortList(List<Item> items){
		Comparator<Item> comparator
	      = (item1, item2) -> Double.valueOf(item1.getPrice()).compareTo(Double.valueOf(item2.getPrice()));
	    
	      items.sort(comparator);
	      return items;
	}
	
	
	private List<Item> getPriceItems(List<String> items) {
		List<Item> priceItems = new ArrayList<>();
			try{
				priceItems = restItemsClient.getItemsInfo(items);
			}catch (BusinessException ex) {
				System.out.print(ex.getMessage());
			}catch (TechnicalException ex) {
				throw ex;
			}
		return priceItems;
	}

}
