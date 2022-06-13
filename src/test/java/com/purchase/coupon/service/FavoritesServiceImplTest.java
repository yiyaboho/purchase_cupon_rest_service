package com.purchase.coupon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.purchase.coupon.model.CouponItem;
import com.purchase.coupon.repository.ItemRepository;
import com.purchase.coupon.repository.UserRepository;
import com.purchase.coupon.repository.model.Favorites;
import com.purchase.coupon.repository.model.Item;
import com.purchase.coupon.repository.model.User;
import com.purchase.coupon.repository.model.UserItem;

@SpringBootTest
class FavoritesServiceImplTest {

	@InjectMocks
	FavoritesServiceImpl favoritesService;
	
	@Mock 
	ItemRepository itemRepository;
	
	@Mock
	UserRepository userRepository;
	
	
	static List<Favorites> favorites;
	
	static CouponItem[] favoriteCoupons;
	
	@BeforeAll
	 static void setUp() {
		MockitoAnnotations.openMocks(FavoritesServiceImplTest.class);
		favorites = new ArrayList<>();
		
		favorites.add(new Bookmark("MLA4", 87));
		favorites.add(new Bookmark("MLA1", 25));
		favorites.add(new Bookmark("MLA2", 10));
		favorites.add(new Bookmark("MLA5", 5));
		favorites.add(new Bookmark("MLA3", 1));
		
		favoriteCoupons = new CouponItem[5];
		favoriteCoupons[0] = new CouponItem("MLA4", 87);
		favoriteCoupons[1] = new CouponItem("MLA1", 25);
		favoriteCoupons[2] = new CouponItem("MLA2", 10);
		favoriteCoupons[3] = new CouponItem("MLA5", 5);
		favoriteCoupons[4] = new CouponItem("MLA3", 1);
	}
	
	@Test
	void getCupontItems() throws Exception{
		when(itemRepository.findTopFavorites(PageRequest.of(0,5))).thenReturn(favorites);
		
		CouponItem[] result = favoritesService.getTopFavorites(5);
		
		assertNotNull(result);
		assertEquals(favoriteCoupons[0], result[0]);
	}
	
	@Test
	void setFavorite() {
		Item item = new Item();
		item.setDescription("Kit 6 Sillas Eames Patas En Madera Para Comedor - Sala");
		item.setItemId("MLA1112351518");
		
		User user = new User();
		user.setLogin("neque.morbi");
		user.setUserId("tatelite");
		when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
		when(itemRepository.save(item)).thenReturn(item);
		
		assertNotNull(item);
	}
	

}
