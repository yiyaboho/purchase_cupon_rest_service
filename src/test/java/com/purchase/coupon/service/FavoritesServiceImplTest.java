package com.purchase.coupon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.purchase.coupon.model.CouponItem;
import com.purchase.coupon.repository.FavoritesRepository;

@SpringBootTest
class FavoritesServiceImplTest {

	@Mock
	FavoritesRepository restFavoritesClient;
	
	@InjectMocks
	FavoritesServiceImpl favoritesService;
	
	static Map<String, Integer> favorites;
	
	@BeforeAll
	 static void setUp() {
		MockitoAnnotations.openMocks(FavoritesServiceImplTest.class);
		favorites = new HashMap<>();
		favorites.put("MLA1", 100);
		favorites.put("MLA2", 210);
		favorites.put("MLA3", 260);
		favorites.put("MLA4", 80);
		favorites.put("MLA5", 90);
	}
	
	@Test
	void getCupontItems() throws Exception{
		when(restFavoritesClient.getTopFavorites(5)).thenReturn(favorites);
		
		CouponItem[] result = favoritesService.getTopFavorites(5);
		
		assertNotNull(result);
		assertEquals(5, result.length);
	}

}
