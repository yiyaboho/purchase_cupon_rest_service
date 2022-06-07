package com.purchase.coupon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.purchase.coupon.model.ItemsToBy;
import com.purchase.coupon.webclient.RestItemsClient;
import com.purchase.coupon.webclient.model.Item;

@SpringBootTest
public class CouponServiceImplTest {

	@Mock
	RestItemsClient restClient;
	
	@InjectMocks
	CouponServiceImpl couponService;
	
	static List<Item> itemsInfo;
	
	@BeforeAll
	 static void setUp() {
		MockitoAnnotations.openMocks(CouponServiceImplTest.class);
		itemsInfo = new ArrayList<Item>();
		itemsInfo.add(new Item("MLA1", 100d));
		itemsInfo.add(new Item("MLA2", 210d));
		itemsInfo.add(new Item("MLA3", 260d));
		itemsInfo.add(new Item("MLA4", 80d));
		itemsInfo.add(new Item("MLA5", 90d));
	}
	
	@Test
	public void getCupontItems() throws Exception{
		List<String> itemsId = Arrays.asList(new String[]{"MLA1", "MLA2", "MLA3", "MLA4", "MLA5"});
		when(restClient.getItemsInfo(itemsId)).thenReturn(itemsInfo);
		
		ItemsToBy result = couponService.getItemsToBy(Arrays.asList(new String[]{"MLA1", "MLA2", "MLA3", "MLA4", "MLA5"}), 500d);
		
		assertEquals(480d,result.getTotal());
		assertNotNull(result.getItemsIds());
	}

	@Test
	public void setUserFavorite(){
		assertEquals("hola", "hola");
	}
	
	@Test
	public void getTopFavorites(){
		} 
}
