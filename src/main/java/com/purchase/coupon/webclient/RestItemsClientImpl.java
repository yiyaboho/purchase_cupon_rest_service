package com.purchase.coupon.webclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.purchase.coupon.exception.BusinessException;
import com.purchase.coupon.exception.TechnicalException;
import com.purchase.coupon.webclient.model.Item;
import com.purchase.coupon.webclient.model.MultiGetItem;

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
	public Item getItemInfo(String itemId) {
		String url = endpointApiItems+"/"+itemId;
		 return this.webClient.get().uri(url)
				 .retrieve()
				 .onStatus(httpStatus -> httpStatus.is4xxClientError(), clientError -> Mono.error(new BusinessException("Se presento un error")))
				 .onStatus(httpStatus -> httpStatus.is5xxServerError(), clientError -> Mono.error(new TechnicalException("Se presento un error tecnico")))
		.bodyToMono(Item.class)
		.block();
		
	}

	@Override
	public List<Item> getItemsInfo(List<String> itemsId) {
		StringBuffer url;
		List<Item> items = new ArrayList<Item>();
		
		for(int i = 0; i<itemsId.size() ; i++) {
			url = new StringBuffer(endpointApiItems+"?ids=");
			int j;
			for(j=i; j < 20 && j<itemsId.size() ; j++) {
				url = url.append(itemsId.get(j));
				url = url.append(",");
			}
			i=j;
			url = url.deleteCharAt(url.length()-1);
			MultiGetItem[] tmpItemsInfo  = this.webClient.get().uri(url.toString())
				 .retrieve()
				 .onStatus(httpStatus -> httpStatus.is4xxClientError(), clientError -> Mono.error(new BusinessException("Se presento un error")))
				 .onStatus(httpStatus -> httpStatus.is5xxServerError(), clientError -> Mono.error(new TechnicalException("Se presento un error tecnico")))
				 .bodyToMono(MultiGetItem[].class)
				 .block();
			items.addAll(Arrays.asList(tmpItemsInfo).stream()
					.filter(item -> item.getCode().equals("200"))
					.map(item->item.getBody())
					.collect(Collectors.toList()));
		}
		
		return items;
	}
}
