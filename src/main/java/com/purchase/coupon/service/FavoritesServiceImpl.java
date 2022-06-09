package com.purchase.coupon.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.purchase.coupon.exception.BusinessException;
import com.purchase.coupon.model.CouponItem;
import com.purchase.coupon.repository.ItemRepository;
import com.purchase.coupon.repository.UserRepository;
import com.purchase.coupon.repository.model.Favorites;
import com.purchase.coupon.repository.model.Item;
import com.purchase.coupon.repository.model.User;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class FavoritesServiceImpl implements FavoritesService {

	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Item setFavorite(String userId, String itemId) {
		log.info("setFavorite init");
		
		Item favoriteItem;
		User user;
		
		favoriteItem = validateItem(itemRepository.findById(itemId));
		
		user = validateUser(userRepository.findById(userId));
		
		Set<User> userList = favoriteItem.getUsers();
		userList.add(user);
		
		favoriteItem.setUsers(userList);
		favoriteItem = itemRepository.save(favoriteItem);
		
		log.info("setFavorite end");
		
		return favoriteItem;
	}
	
	private Item validateItem(Optional<Item> optionalItem) throws BusinessException{
		if(optionalItem.isPresent()) {
			return optionalItem.get();
		}else {
			throw new BusinessException("No se encontro informacion del item");
		}
	}
	
	private User validateUser(Optional<User> optionalUser) throws BusinessException{
		if(optionalUser.isPresent()) {
			return optionalUser.get();
		}else {
			throw new BusinessException("No se encontro informacion del usuario");
		}
	}

	@Override
	public CouponItem[] getTopFavorites(int top) {
		log.info("getTopFavorites init");
		List<Favorites> favorites = itemRepository.findTopFavorites(PageRequest.of(0,top));
		CouponItem[] response = new CouponItem[favorites.size()];

		int i = 0;
		
		for (Favorites entry : favorites) {
			response[i++] = new CouponItem(entry.getItemId(), entry.getQuantity());			
		}
		log.info("getTopFavorites end");
		return response;
	}

}
