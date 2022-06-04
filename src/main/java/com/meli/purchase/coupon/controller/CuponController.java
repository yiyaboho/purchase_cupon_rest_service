package com.meli.purchase.coupon.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meli.purchase.coupon.model.ItemsToBy;

@RestController
@RequestMapping(path = "/coupon")
public class CuponController {

	@PostMapping(consumes = "application/json", produces = "application/json")
	public ResponseEntity<ItemsToBy> getCupontItems(@RequestBody(required = true) ItemsToBy requestBody){
		return ResponseEntity.ok(null);
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
