package com.cats.retailer;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cats.retailer.controller.RetailerController;
import com.cats.retailer.entity.Transaction;
import com.cats.retailer.service.RetailerService;

@SuppressWarnings("removal")
@WebMvcTest(RetailerController.class)
class RetailerControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RetailerService retailerService;

	@Test
	void testSaveTransaction() throws Exception {
		Transaction transaction = new Transaction();
		transaction.setId(1L);
		transaction.setCustomerEmail("customer1@gmail.com");
		transaction.setTransactionAmount(100);

		Mockito.when(retailerService.saveTransaction(Mockito.any(Transaction.class))).thenReturn(transaction);

		mockMvc.perform(post("/api/retailer/save/transaction").contentType(MediaType.APPLICATION_JSON)
				.content("{\"transactionAmount\": 100}")).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1L))
				.andExpect(jsonPath("$.customerEmail").value("customer1@gmail.com"))
				.andExpect(jsonPath("$.transactionAmount").value(100));
	}

	@Test
	void testFindAllTransactions() throws Exception {
		Transaction t1 = new Transaction();
		t1.setId(1L);
		t1.setTransactionAmount(100);
		Transaction t2 = new Transaction();
		t2.setId(2L);
		t2.setTransactionAmount(200);
		List<Transaction> transactions = Arrays.asList(t1, t2);

		Mockito.when(retailerService.findAllTransactions()).thenReturn(transactions);

		mockMvc.perform(get("/api/retailer/all/transactions").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.length()").value(2))
				.andExpect(jsonPath("$[0].id").value(1L)).andExpect(jsonPath("$[0].transactionAmount").value(100))
				.andExpect(jsonPath("$[1].id").value(2L)).andExpect(jsonPath("$[1].transactionAmount").value(200));
	}

	@Test
	void testAllCustomerRewardPoints() throws Exception {
		Map<String, Map<Month, Integer>> rewards = new HashMap<>();
		Map<Month, Integer> monthlyRewards = new HashMap<>();
		monthlyRewards.put(Month.JANUARY, 100);
		monthlyRewards.put(Month.FEBRUARY, 200);
		rewards.put("customer1@gmail", monthlyRewards);

		Mockito.when(retailerService.getAllCustomerRewards()).thenReturn(rewards);
		
		mockMvc.perform(get("/api/retailer/all/rewards").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$.customer1@gmail.JANUARY").value(100))
				.andExpect(jsonPath("$.customer1@gmail.FEBRUARY").value(200));
	}

	@Test
	void testGetCustomerRewardsByEmail() throws Exception {
		String emailId = "customer1@gmail.com";
        
		Map<String, Map<Month, Integer>> rewards = new HashMap<>();
		Map<Month, Integer> monthlyRewards = new HashMap<>();
		monthlyRewards.put(Month.JANUARY, 100);
		monthlyRewards.put(Month.FEBRUARY, 200);
		rewards.put("customer1@gmail.com", monthlyRewards);

		Mockito.when(retailerService.getCustomerRewardsByEmailId(emailId)).thenReturn(rewards);
		
		mockMvc.perform(get("/api/retailer/rewards/"+emailId).contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.['customer1@gmail.com'].JANUARY").value(100))
                .andExpect(jsonPath("$.['customer1@gmail.com'].FEBRUARY").value(200));
	}
}
