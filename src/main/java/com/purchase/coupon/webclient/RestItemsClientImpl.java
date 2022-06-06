package com.purchase.coupon.webclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import com.purchase.coupon.exception.BusinessException;
import com.purchase.coupon.exception.TechnicalException;
import com.purchase.coupon.webclient.model.Item;

import reactor.core.publisher.Mono;

@Service
public class RestItemsClientImpl implements RestItemsClient{
	
	private final WebClient webClient;
	
	@Value("${app.api-items.endpoint}")
	private String endpointApiItems;
	
	public RestItemsClientImpl(WebClient.Builder builder) {
		this.webClient = builder.baseUrl(endpointApiItems).build();
	}
	
	@Override
	public Item getItemPrice(String itemId) {
		String url = endpointApiItems+"/"+itemId;
		 return this.webClient.get().uri(url)
				 .retrieve()
				 .onStatus(httpStatus -> httpStatus.is4xxClientError(), clientError -> Mono.error(new BusinessException("Se presento un error")))
				 .onStatus(httpStatus -> httpStatus.is5xxServerError(), clientError -> Mono.error(new TechnicalException("Se presento un error tecnico")))
		.bodyToMono(Item.class)
		.block();
		
	}
}
