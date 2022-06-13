package com.purchase.coupon.webclient;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.web.reactive.function.client.WebClient;

import com.purchase.coupon.webclient.model.Item;
import com.purchase.coupon.webclient.model.MultiGetItem;

import okhttp3.mockwebserver.MockWebServer;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestItemsClientImplTest {
	
	@LocalServerPort
    private static int port;

	private static WebClient.Builder builder = WebClient.builder();

	@Mock
	static WebClient webClient;

	@InjectMocks
	static RestItemsClientImpl restClient = new RestItemsClientImpl(builder);

	@SuppressWarnings("rawtypes")
	@Mock
	private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;

	@SuppressWarnings("rawtypes")
	@Mock
	private WebClient.RequestHeadersSpec requestHeadersSpecMock;

	static Item item;
	@Mock
	Mono<Item> monoItem = Mono.just(item);
	
	static MultiGetItem[] responseItems;
	@Mock
	//Mono<MultiGetItem[]> monoMultiItems = Mono.just(responseItems);
	Mono<MultiGetItem[]> monoMultiItems = Mono.just(responseItems);

	@SuppressWarnings("rawtypes")
	@Mock
	private WebClient.ResponseSpec responseSpecMock;

	public static MockWebServer mockBackEnd = new MockWebServer();

	static String endpointApiItems = "http://localhost:PORT/items";

	static String itemId = "MLA916058884";
	

	@BeforeAll
	static void RestItemsClientImpl()  {
		initMultiGetItemResponse();
		item = new Item("MLA1112351518", 800d);
		
		MockitoAnnotations.openMocks(RestItemsClientImplTest.class);

		startMockServer();
		
		
		
	}
	
	private static void startMockServer() {
		try {
			mockBackEnd.start();
			
			endpointApiItems = endpointApiItems.replaceFirst("PORT", String.valueOf(mockBackEnd.getPort()));
			//endpointApiItems = endpointApiItems.replaceFirst("PORT", String.valueOf(port));
			
			restClient.endpointApiItems = endpointApiItems;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private static void initMultiGetItemResponse() {
		responseItems = new MultiGetItem[6];
		responseItems[0] = new MultiGetItem("MLA4", new Item("MLA4", 250d));
		responseItems[1] = new MultiGetItem("MLA1", new Item("MLA1", 30d));
		responseItems[2] = new MultiGetItem("MLA2", new Item("MLA2", 56d));
		responseItems[3] = new MultiGetItem("MLA5", new Item("MLA5", 97d));
		responseItems[4] = new MultiGetItem("MLA3", new Item("MLA3", 100d));
		
		MultiGetItem errorItem = new MultiGetItem(itemId, item);
		errorItem.setCode("404");
		responseItems[5] = errorItem;
		
		
	}

	@AfterAll
	static void tearDown() throws IOException {
		mockBackEnd.shutdown();
	}
	
	//@Test
	/*void getItemInfo() {
		String url = endpointApiItems + "/" + itemId;

		when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
		when(requestHeadersUriSpecMock.uri(url)).thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(Item.class)).thenReturn(monoItem);
		when(monoItem.block()).thenReturn(item);

		Item returnItem = restClient.getItemInfo(itemId);

		assertNotNull(returnItem);
		assertEquals(itemId, returnItem.getId());

	}*/

	//@Test
	void getIListItemsInfo() {
		List<String> itemsId = Arrays.asList(new String[] { "MLA1", "MLA2", "MLA3", "MLA4", "MLA5" });
		String url = endpointApiItems + "?ids=MLA1,MLA2,MLA3,MLA4,MLA5";

		when(webClient.get()).thenReturn(requestHeadersUriSpecMock);
		when(requestHeadersUriSpecMock.uri(url)).thenReturn(requestHeadersSpecMock);
		when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpecMock);
		when(responseSpecMock.bodyToMono(MultiGetItem[].class)).thenReturn(monoMultiItems);
		when(monoMultiItems.block()).thenReturn(responseItems);

		List<Item> returnItem = restClient.getListItemsInfo(itemsId);

		//assertNotNull(returnItem);
		assertEquals(6, returnItem.size());
	}

}
