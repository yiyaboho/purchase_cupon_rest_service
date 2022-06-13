package com.purchase.coupon.webclient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.purchase.coupon.exception.BusinessException;
import com.purchase.coupon.exception.TechnicalException;
import com.purchase.coupon.webclient.model.Item;
import com.purchase.coupon.webclient.model.MultiGetItem;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class RestItemsClientImpl implements RestItemsClient{
	
	private final WebClient webClient;
	
	@Value("${app.api-items.endpoint}")
	String endpointApiItems;
	
	public RestItemsClientImpl(WebClient.Builder builder) {
		System.out.print("**********************************************"+endpointApiItems);
		this.webClient = builder.baseUrl(endpointApiItems).build();
	}
	
	public RestItemsClientImpl() {
		this.webClient = WebClient.builder().build();
	}
	

	@Override
	public List<Item> getListItemsInfo(List<String> itemsId) {
		log.info("getItemIfo items endpoint: {} itemsIds: {}", endpointApiItems, itemsId);
		
		List<Item> items = new ArrayList<>();
		
		for(int i = 0; i<itemsId.size() ; i++) {
			StringBuilder url = new StringBuilder(endpointApiItems+"?ids=");
			
			for(int j=i; j < 20 && j<itemsId.size() ; j++, i++) {
				url = url.append(itemsId.get(i));
				url = url.append(",");
			}
			
			url = url.deleteCharAt(url.length()-1);
			
			/*List<MultiGetItem> tmpItemsInfo  = this.webClient.get().uri(url.toString())
					 .retrieve()
					 .onStatus(HttpStatus::is4xxClientError, clientError -> Mono.error(new BusinessException("Error consultando datos")))
					 .onStatus(HttpStatus::is5xxServerError, clientError -> Mono.error(new TechnicalException("Se presento un error tecnico")))
					 .bodyToFlux(MultiGetItem.class)
					 .collectList().block();
		
			if(tmpItemsInfo != null)
				items.addAll(tmpItemsInfo.stream()
						.filter(item -> item.getCode().equals("200"))
						.map(item -> item.getBody())
						.collect(Collectors.toList()));*/
			
			MultiGetItem[] tmpItemsInfo  = this.webClient.get().uri(url.toString())
					 .retrieve()
					 .onStatus(HttpStatus::is4xxClientError, clientError -> Mono.error(new BusinessException("Error consultando datos")))
					 .onStatus(HttpStatus::is5xxServerError, clientError -> Mono.error(new TechnicalException("Se presento un error tecnico")))
					 .bodyToMono(MultiGetItem[].class)
					 .block();
				
				items.addAll(Arrays.asList(tmpItemsInfo).stream()
						.filter(item -> item.getCode().equals("200"))
						.map(item -> item.getBody())
						.collect(Collectors.toList()));
		}
		
		log.info("getItemIfo response items info: {}", items);
		
		return items;
	}
}
