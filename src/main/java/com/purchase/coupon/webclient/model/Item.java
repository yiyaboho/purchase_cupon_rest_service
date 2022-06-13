package com.purchase.coupon.webclient.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter @Setter @ToString @NoArgsConstructor
public class Item {
	private String id;
	
	@JsonProperty("site_id")
    private String siteId;
    
	private String title;
    
	@JsonProperty("seller_id")
    private String sellerId;
    
	@JsonProperty("category_id")
    private String categoryId;
    
	private double price;
    private String message;
    private String error;
    private String status;
    
	public Item(String id, double price) {
		super();
		this.id = id;
		this.price = price;
	}


}
