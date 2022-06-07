package com.purchase.coupon.webclient.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class MultiGetItem {
	
	private String code;
	private Item body;

}
