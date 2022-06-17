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
import com.purchase.coupon.repository.FavoriteRepository;
import com.purchase.coupon.repository.ItemRepository;
import com.purchase.coupon.repository.UserRepository;
import com.purchase.coupon.repository.model.Favorites;
import com.purchase.coupon.repository.model.Item;
import com.purchase.coupon.repository.model.User;

import lombok.extern.slf4j.Slf4j;

/**
 * Clase encargada de implementar la logica para almacenar y consultar informacion sobre items favoritos
 * @author bohor
 *
 */
@Service
@Slf4j
@Transactional
public class FavoritesServiceImpl implements FavoritesService {

	
	@Autowired
	private ItemRepository itemRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private FavoriteRepository favoritesRepository;
	
	@Override
	public Item setFavorite(String userId, String itemId) {
		log.info("setFavorite init");
		
		Item favoriteItem;
		User user;
		Favorites favorites;
		
		favoriteItem = validateItem(itemRepository.findById(itemId));
		favorites = validateFavorites(favoritesRepository.findById(itemId), itemId);
		
		user = validateUser(userRepository.findById(userId));
		
		Set<User> userList = favoriteItem.getUsers();
		if(userList.add(user)) {
			
			favoriteItem.setUsers(userList);
			favoriteItem = itemRepository.save(favoriteItem);
			
			favorites.setQuantity(favorites.getQuantity() + 1);
			favoritesRepository.save(favorites);
			
		}
		
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
	
	private Favorites validateFavorites(Optional<Favorites> optionalFavorites, String itemId) throws BusinessException{
		if(optionalFavorites.isPresent()) {
			return optionalFavorites.get();
		}else {
			return new Favorites(itemId, 0);
		}
	}

	@Override
	public CouponItem[] getTopFavorites(int top) {
		log.info("getTopFavorites init");
		List<Favorites> favorites = favoritesRepository.findTopFavorites(PageRequest.of(0,top));
		CouponItem[] response = new CouponItem[favorites.size()];

		int i = 0;
		
		for (Favorites entry : favorites) {
			response[i++] = new CouponItem(entry.getItemId(), entry.getQuantity());			
		}
		log.info("getTopFavorites end");
		return response;
	}

	@Override
	public User deleteFavorite(String userId, String itemId) {
		log.info("deleteFavorite init");
		
		Item favoriteItem;
		User user;
		Favorites favorites;
		
		favoriteItem = validateItem(itemRepository.findById(itemId));
		favorites = validateFavorites(favoritesRepository.findById(itemId), itemId);
		
		user = validateUser(userRepository.findById(userId));
		
		Set<Item> itemsList = user.getItems();
		if(itemsList.remove(favoriteItem)) {
			
			user.setItems(itemsList);
			user = userRepository.save(user);
			
			favorites.setQuantity(favorites.getQuantity() - 1);
			favoritesRepository.save(favorites);
			
		}
		
		log.info("deleteFavorite end");
		
		return user;
	}

}
