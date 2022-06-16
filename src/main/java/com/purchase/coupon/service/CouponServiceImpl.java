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

import lombok.extern.slf4j.Slf4j;

/**
 * Clase que contienen la logica de negocio relacionada al cupon 
 * @author bohor
 *
 */
@Service
@Slf4j
public class CouponServiceImpl implements CouponService {

	@Autowired
	private RestItemsClient restItemsClient;
	
	@Override
	public ItemsToBy getItemsToBy(List<String> items, double amount) throws BusinessException{
		log.info("ItemsToBy init");
		List<Item> priceItems = getPriceItems(items);

		ItemsToBy couponItems = findBestListOfItems(priceItems, amount);

		if(couponItems == null)
			throw new BusinessException("No se encontraron items que cumplan con el criterio");
		
		log.info("ItemsToBy end");
		return couponItems;
	}
	
	/**
	 * Funcion para calcular la lista de items que maximiza lo gastado
	 * @param priceItems : lista de items con su precio
	 * @param amount : valor del cupon
	 * @return : identificador de los items y el monto total 
	 */
	private ItemsToBy findBestListOfItems(List<Item> priceItems, double amount){
		List<String> couponItems;
		List<ItemsToBy> tmpTree = new ArrayList<>();

		while(!priceItems.isEmpty()) {
			couponItems = new ArrayList<>();
			double total = 0;
			for (Item item : priceItems) {
				if (item.getPrice() < amount) {
					double tmpTotal = total + item.getPrice();
					if (tmpTotal <= amount) {
						total = tmpTotal;
						couponItems.add(item.getId());
					}
				}
			}
			tmpTree.add(new ItemsToBy(couponItems, total));
			priceItems.remove(0);
		}

		if(!tmpTree.isEmpty()) {
			Comparator<ItemsToBy> comparator
					= (item1, item2) -> item1.getTotal().compareTo(item2.getTotal());

			tmpTree.sort(comparator.reversed());
			return tmpTree.get(0);
		}
		return null;
	}
	
	
	private List<Item> getPriceItems(List<String> items) {
		List<Item> priceItems = new ArrayList<>();
			try{
				priceItems = restItemsClient.getListItemsInfo(items);
			}catch (BusinessException ex) {
				log.error("ItemsToBy BusinessException {}", ex.getMessage());
			}catch (TechnicalException ex) {
				throw ex;
			}
		return priceItems;
	}

}
