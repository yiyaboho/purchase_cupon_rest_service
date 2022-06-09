package com.purchase.coupon.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.purchase.coupon.repository.model.UserItem;

@SpringBootTest
class FavoritesServiceImplTest {

	@InjectMocks
	FavoritesServiceImpl favoritesService;
	
	static List<UserItem> favorites;
	
	@BeforeAll
	 static void setUp() {
		MockitoAnnotations.openMocks(FavoritesServiceImplTest.class);
		favorites = new ArrayList<>();
		/*favorites.add(new Favorite()"MLA1", 100);
		favorites.put("MLA2", 210);
		favorites.put("MLA3", 260);
		favorites.put("MLA4", 80);
		favorites.put("MLA5", 90);*/
	}
	
	@Test
	void getCupontItems() throws Exception{
		//when(restFavoritesClient.findTopFavorites(PageRequest.of(0,5))).thenReturn(favorites);
		
		//CouponItem[] result = favoritesService.getTopFavorites(5);
		
		//assertNotNull(result);
		//assertEquals(5, result.length);
	}

}
