package com.purchase.coupon.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

	/**
	 * Permite calcular los items que tendría que comprar el usuario de acuerdo a
	 * los parametros de entrada
	 * 
	 * @param requestBody : identificadores de los items y monto del cupon
	 * @return : los items que deberia comprar el usuario y el monto total
	 */
	@PostMapping(value = "/coupon", consumes = "application/json", produces = "application/json")
	public ResponseEntity<ItemsToBy> getCupontItems(@RequestBody(required = true) ItemsToBy requestBody) {
		log.info("getCupontItems request: {}", requestBody);

		ItemsToBy items2By;

		items2By = couponService.getItemsToBy(requestBody.getItemsIds(), requestBody.getAmount());

		log.info("getCupontItems response: {}", items2By);

		return ResponseEntity.ok(items2By);

	}

	/**
	 * Permite almacenar la informacion de un item marcado como favorito por un
	 * usuario
	 * 
	 * @param userId : identificador del usuario
	 * @param itemId : identificador del item marcado como favorito
	 * @return
	 */
	@PostMapping(value = "/coupon/favorite/user/{userId}/item/{itemId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> setUserFavorite(@PathVariable(name = "userId") String userId,
			@PathVariable(name = "itemId") String itemId) {
		log.info("setUserFavorite request userId: {}, request itemId {}", userId, itemId);

		favoriteService.setFavorite(userId, itemId);
		return ResponseEntity.ok("Datos guardados");
	}
	

	/**
	 * Permite eliminar la informacion de un item marcado como favorito
	 * 
	 * @param userId : identificador del usuario
	 * @param itemId : identificador del item marcado como favorito que se va a eliminar
	 * @return
	 */
	@DeleteMapping(value = "/coupon/favorite/user/{userId}/item/{itemId}", consumes = "application/json", produces = "application/json")
	public ResponseEntity<String> deleteUserFavorite(@PathVariable(name = "userId") String userId,
			@PathVariable(name = "itemId") String itemId) {
		log.info("deleteUserFavorite request userId: {}, request itemId {}", userId, itemId);

		favoriteService.deleteFavorite(userId, itemId);
		return ResponseEntity.ok("Datos guardados");
	}

	/**
	 * Permite consulta el top de items que mas se han marcado como favoritos
	 * 
	 * @param top : parametro opcional para indicar el numero de registros para
	 *            retornar, si no se envia se toma el valor 5 por defecto
	 * @return : identificador del item y la cantidad de veces que se ha marcado
	 *         como favorito por los usuarios
	 */
	@GetMapping(value = "/coupon/stats", consumes = "application/json", produces = "application/json")
	public ResponseEntity<CouponItem[]> getTopFavorites(@RequestParam(name = "top", required = false) Integer top) {
		log.info("getTopFavorites top {}", top);
		if (top == null)
			top = 5;
		CouponItem[] favorites = favoriteService.getTopFavorites(top);
		log.info("getTopFavorites response{}", Arrays.asList(favorites));
		return ResponseEntity.ok(favorites);
	}
}
