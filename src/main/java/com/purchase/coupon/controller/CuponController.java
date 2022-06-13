package com.purchase.coupon.controller;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.purchase.coupon.exception.BusinessException;
import com.purchase.coupon.exception.TechnicalException;
import com.purchase.coupon.model.CouponItem;
import com.purchase.coupon.model.ItemsToBy;
import com.purchase.coupon.service.CouponService;
import com.purchase.coupon.service.FavoritesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(path = "/")
@Slf4j
public class CuponController {
	
	@Autowired
	private CouponService couponService;
	
	@Autowired
	private FavoritesService favoriteService;

	
	@PostMapping(value = "/coupon", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ItemsToBy> getCupontItems(@RequestBody(required = true) ItemsToBy requestBody){
		log.info("getCupontItems request: {}", requestBody );
		ItemsToBy items2By;
	
		try {
			items2By = couponService.getItemsToBy(requestBody.getItemsIds(), requestBody.getAmount());
		
			log.info("getCupontItems response: {}", items2By );
			
			return ResponseEntity.ok(items2By);
		} catch (TechnicalException e) {
			log.error("getCupontItems error: {}", e.getMessage());
			throw e;
		}catch(BusinessException e) {
			log.error("getCupontItems error: {}", e.getMessage());
			ItemsToBy notFound = new ItemsToBy();
			notFound.setMessage(e.getMessage());
			return new ResponseEntity<>(notFound, HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PostMapping(value="/coupon/favorite/user/{userId}/item/{itemId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setUserFavorite(@PathVariable(name="userId") String userId, @PathVariable(name="itemId")String itemId){
		log.info("setUserFavorite request userId: {}, request itemId {}", userId, itemId );
		favoriteService.setFavorite(userId, itemId);
		return ResponseEntity.ok(null);
	}
	
	@GetMapping(value="/coupon/stats", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CouponItem[]> getTopFavorites(@RequestParam(name="top", required = false) Integer top){
		log.info("getTopFavorites top {}", top);
		if(top == null)
			top = 5;
		CouponItem[] favorites = favoriteService.getTopFavorites(top);
		log.info("getTopFavorites response{}", Arrays.asList(favorites));
		return ResponseEntity.ok(favorites);
	} 
}
