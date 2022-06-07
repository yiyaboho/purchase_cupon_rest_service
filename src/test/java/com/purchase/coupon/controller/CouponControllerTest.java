package com.purchase.coupon.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.purchase.coupon.model.ItemsToBy;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CouponControllerTest {

	@LocalServerPort
	private int port;
	
	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void getCupontItems(){
		ItemsToBy itemsList = new ItemsToBy();
		itemsList.setAmount(545);
		itemsList.setItemsIds(new ArrayList<>());
		
		ResponseEntity<String> result = this.restTemplate.postForEntity("http://localhost:" + port + "/coupon", itemsList, String.class);
        
		assertThat(result.getBody().contains("Respuesta de /coupon"));
	}
	
	@Test
	public void setUserFavorite(){
		}
	
	@Test
	public void getTopFavorites(){
		} 
}
