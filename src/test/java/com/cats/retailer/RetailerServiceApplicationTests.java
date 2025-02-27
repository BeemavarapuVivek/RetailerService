package com.cats.retailer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.cats.retailer.controller.RetailerController;
import com.cats.retailer.service.RetailerService;

@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.RANDOM_PORT)
class RetailerServiceApplicationTests {

	@LocalServerPort
	private int port;

	@Mock
	private RetailerService retailerService;

	@InjectMocks
	RetailerController retailerController;

	private TestRestTemplate restTemplate = new TestRestTemplate();

	@Test
	void getAllCustomerRewardPointsTest() {
		MockitoAnnotations.openMocks(this);
		Map<String, Map<Month, Integer>> customerPonts = new HashMap<>();
		Map<Month, Integer> monthlyPoints = new HashMap<>();
		monthlyPoints.put(Month.FEBRUARY, 80);
		customerPonts.put("customer3@gmail.com", monthlyPoints);

		when(retailerService.getAllCustomerRewards()).thenReturn(customerPonts);

//		String url="http://localhost:"+port+"/api/retailer/all/rewards";

		String url = "http://localhost:" + port + "/api/retailer/all/rewards";
		// System.out.println(url);
		ResponseEntity<Map> response = restTemplate.getForEntity(url, Map.class);
		assertEquals(200, response.getStatusCodeValue());
	}

}
