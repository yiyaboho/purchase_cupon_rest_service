package com.purchase.coupon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.purchase.coupon.exception.BusinessException;
import com.purchase.coupon.exception.TechnicalException;
import com.purchase.coupon.model.ItemsToBy;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j

public class ExceptionHandlerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ItemsToBy> handleException(BusinessException e) {
    	log.error("Business error {}", e.getMessage());
    	
    	ItemsToBy notFound = new ItemsToBy();
		notFound.setMessage(e.getMessage());
    	
		return ResponseEntity.ok(notFound);
    } 
    
    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<String> handleException(TechnicalException e) {
    	log.error("Technical error {}", e.getMessage());
        
    	return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
    	log.error("General error {}", e.getMessage());
        
    	return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Ocurrio un error tecnico");
    }
}