package com.purchase.coupon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.core.util.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.purchase.coupon.exception.BusinessException;
import com.purchase.coupon.exception.TechnicalException;
import com.purchase.coupon.model.ItemsToBy;
import com.purchase.coupon.webclient.RestItemsClient;
import com.purchase.coupon.webclient.model.Item;

@SpringBootTest
class CouponServiceImplTest {

	@Mock
	RestItemsClient restItemsClient;

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
	void getCupontItems() throws Exception {
		List<String> itemsId = Arrays.asList(new String[] { "MLA1", "MLA2", "MLA3", "MLA4", "MLA5" });
		when(restItemsClient.getListItemsInfo(itemsId)).thenReturn(itemsInfo);

		ItemsToBy result = couponService
				.getItemsToBy(Arrays.asList(new String[] { "MLA1", "MLA2", "MLA3", "MLA4", "MLA5" }), 500d);

		assertEquals(480d, result.getTotal());
		assertNotNull(result.getItemsIds());
	}
	
	@Test
	void getCupontItemsCase2() throws Exception {
		List<String> itemsId = Arrays.asList(new String[] { "MLA1", "MLA2", "MLA3", "MLA4", "MLA5" });
		when(restItemsClient.getListItemsInfo(itemsId)).thenReturn(getItemsCase2());

		ItemsToBy result = couponService
				.getItemsToBy(Arrays.asList(new String[] { "MLA1", "MLA2", "MLA3", "MLA4", "MLA5" }), 6000d);

		assertEquals(100d, result.getTotal());
		
		assertNotNull(result.getItemsIds());
	}
	
	private List<Item> getItemsCase2(){
		List<Item> itemsInfoCase2 = new ArrayList<Item>();
		itemsInfo.add(new Item("MLA1", 100d));
		itemsInfo.add(new Item("MLA2", 20010d));
		itemsInfo.add(new Item("MLA3", 26000d));
		itemsInfo.add(new Item("MLA4", 8000d));
		itemsInfo.add(new Item("MLA5", 5999d));
		return itemsInfoCase2;
	}
	

	@Test
	void getCupontItemsBusinessException() throws Exception {
		List<String> itemsId = Arrays.asList(new String[] { "MLA1", "MLA2", "MLA3", "MLA4", "MLA5" });
		when(restItemsClient.getListItemsInfo(itemsId)).thenThrow(new BusinessException("Se presento un error"));

		try {
			couponService
					.getItemsToBy(Arrays.asList(new String[] { "MLA1", "MLA2", "MLA3", "MLA4", "MLA5" }), 500d);
		} catch (Exception ex) {
			assertInstanceOf(BusinessException.class, ex);
		}
	}
	
	@Test
	void getCupontItemsTechnicalException() throws Exception {
		List<String> itemsId = Arrays.asList(new String[] { "MLA1", "MLA2", "MLA3", "MLA4", "MLA5" });
		when(restItemsClient.getListItemsInfo(itemsId)).thenThrow(new TechnicalException("Se presento un error"));

		try {
			couponService
					.getItemsToBy(Arrays.asList(new String[] { "MLA1", "MLA2", "MLA3", "MLA4", "MLA5" }), 500d);
		} catch (Exception ex) {
			assertInstanceOf(TechnicalException.class, ex);
		}
	}

}
