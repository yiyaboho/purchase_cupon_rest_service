package com.purchase.coupon.controller;

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

import com.purchase.coupon.model.ItemsToBy;
import com.purchase.coupon.service.CouponService;

@RestController
@RequestMapping(path = "/coupon")
public class CuponController {
	
	@Autowired
	private CouponService couponService;

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<ItemsToBy> getCupontItems(@RequestBody(required = true) ItemsToBy requestBody){
		
		ItemsToBy items2By;
		try {
			items2By = couponService.getItemsToBy(requestBody.getItemsIds(), requestBody.getAmount());
			return ResponseEntity.ok(items2By);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<ItemsToBy>(new ItemsToBy(), HttpStatus.NOT_FOUND);
	}
	
	@PostMapping(value="/favorite/user/{userId}/item/{itemId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> setUserFavorite(@PathVariable(name="userId") String userId, @PathVariable(name="itemId")String itemId){
		return ResponseEntity.ok(null);
	}
	
	@GetMapping(value="/stats", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> getTopFavorites(@RequestParam(name="top") int top){
		return ResponseEntity.ok(null);
	} 
}
