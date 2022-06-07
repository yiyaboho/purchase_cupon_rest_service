package com.purchase.coupon.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.purchase.coupon.exception.BusinessException;
import com.purchase.coupon.exception.TechnicalException;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j

public class ExceptionHandlerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleException(BusinessException e) {
    	
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
    } 
    
    @ExceptionHandler(TechnicalException.class)
    public ResponseEntity<String> handleException(TechnicalException e) {
    	log.error("Controller error {}", e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.getMessage());
    }
}