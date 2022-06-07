package com.purchase.coupon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.purchase.coupon.model.ItemsToBy;

@RestController
@RequestMapping(path = "/coupon")
public class CouponController {

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> getCupontItems(@RequestBody(required = true) ItemsToBy requestBody){
		return ResponseEntity.ok("Respuesta de /coupon");
	}
	
	@PostMapping(value="/favorite/user/{userId}/item/{itemId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> setUserFavorite(@PathVariable(name="userId") String userId, @PathVariable(name="itemId")String itemId){
		return ResponseEntity.ok("Respuesta de /coupon/favorite/user/{userId}/item/{itemId}");
	}
	
	@GetMapping(value="/stats", consumes = "application/json", produces = "application/json")
	public ResponseEntity<Object> getTopFavorites(@RequestParam(name="top") int top){
		return ResponseEntity.ok("Respuesta de /coupon/stats");
	} 
}
