package com.purchase.coupon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;

import com.purchase.coupon.model.CouponItem;
import com.purchase.coupon.repository.ItemRepository;
import com.purchase.coupon.repository.model.Favorites;
import com.purchase.coupon.repository.model.UserItem;

@SpringBootTest
class FavoritesServiceImplTest {

	@InjectMocks
	FavoritesServiceImpl favoritesService;
	
	@Mock 
	ItemRepository itemRepository;
	
	
	static List<Favorites> favorites;
	
	@BeforeAll
	 static void setUp() {
		MockitoAnnotations.openMocks(FavoritesServiceImplTest.class);
		favorites = new ArrayList<>();
		
		favorites.add(new Bookmark("MLA1", 100));
		favorites.add(new Bookmark("MLA2", 210));
		favorites.add(new Bookmark("MLA3", 260));
		favorites.add(new Bookmark("MLA4", 80));
		favorites.add(new Bookmark("MLA5", 90));
	}
	
	@Test
	void getCupontItems() throws Exception{
		when(itemRepository.findTopFavorites(PageRequest.of(0,5))).thenReturn(favorites);
		
		CouponItem[] result = favoritesService.getTopFavorites(5);
		
		assertNotNull(result);
		assertEquals(5, result.length);
	}
	
	

}
