package com.cats.retailer;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cats.retailer.entity.Transaction;
import com.cats.retailer.exception.ResourceNotFoundException;
import com.cats.retailer.repository.RetailerRepository;
import com.cats.retailer.serviceimpl.RetailerServiceImpl;
import com.cats.retailer.util.CustomerUtils;
@SuppressWarnings("deprecation")
@ExtendWith(MockitoExtension.class)
class RetailerServiceTest {
	@Mock
	private RetailerRepository retailerRepository;

	@Mock
	private CustomerUtils customerUtils;

	@InjectMocks
	private RetailerServiceImpl retailerServiceImpl;

	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testSaveTransaction() {
		Transaction transaction = new Transaction("customer1@gmial.com", 120, LocalDate.of(2025, Month.JANUARY, 1));
		when(retailerRepository.save(transaction)).thenReturn(transaction);

		Transaction savedTransaction = retailerServiceImpl.saveTransaction(transaction);
		assertEquals(transaction, savedTransaction);
		verify(retailerRepository).save(transaction);

	}

	@Test
	void findAllTransactions() {
		List<Transaction> transactionList = Arrays.asList(
				new Transaction("customer1@gmial.com", 120, LocalDate.of(2025, Month.JANUARY, 1)),
				new Transaction("customer2@gmial.com", 200, LocalDate.of(2025, Month.FEBRUARY, 1)));
		when(retailerRepository.findAll()).thenReturn(transactionList);

		List<Transaction> actualResults = retailerServiceImpl.findAllTransactions();
		assertEquals(transactionList, actualResults);
		verify(retailerRepository).findAll();
	}

	@Test
	void testGetAllCustomerRewards() throws ResourceNotFoundException, Exception {
		LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
		List<Transaction> transactionList = Arrays.asList(
				new Transaction("customer1@gmial.com", 120, LocalDate.of(2025, Month.JANUARY, 1)),
				new Transaction("customer2@gmial.com", 200, LocalDate.of(2025, Month.FEBRUARY, 1)));

		Map<String, Map<Month, Integer>> expectedRewards = new HashMap<String, Map<Month, Integer>>();
		Map<Month, Integer> customer1Rewards = new HashMap<Month, Integer>();
		customer1Rewards.put(Month.JANUARY, 90);
		expectedRewards.put("customer1@gmial.com", customer1Rewards);
		Map<Month, Integer> customer2Rewards = new HashMap<Month, Integer>();
		customer2Rewards.put(Month.FEBRUARY, 250);
		expectedRewards.put("customer2@gmial.com", customer2Rewards);

		when(retailerRepository.findRecordsFromLastThreeMonths(threeMonthsAgo)).thenReturn(transactionList);
		when(customerUtils.calculateCustomerTransactionRewards(transactionList)).thenReturn(expectedRewards);
		Map<String, Map<Month, Integer>> actualRewards = retailerServiceImpl.getAllCustomerRewards();

		assertEquals(expectedRewards, actualRewards);
	}

	@Test
	void testGetCustomerRewardsByEmail() throws ResourceNotFoundException, Exception {
		String emailId = "customer1@gmail.com";
		LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
		List<Transaction> transactionList = Arrays.asList(
				new Transaction(emailId, 150, LocalDate.of(2025, Month.JANUARY, 1)),
				new Transaction(emailId, 200, LocalDate.of(2025, Month.FEBRUARY, 1)));

		Map<String, Map<Month, Integer>> expectedRewards = new HashMap<>();
		Map<Month, Integer> customer1Rewards = new HashMap<>();
		customer1Rewards.put(Month.JANUARY, 150);
		customer1Rewards.put(Month.FEBRUARY, 250);
		expectedRewards.put(emailId, customer1Rewards);

		when(retailerRepository.findCustomerRecordsFromLastThreeMonths(threeMonthsAgo, emailId))
				.thenReturn(transactionList);
		when(customerUtils.calculateCustomerTransactionRewards(transactionList)).thenReturn(expectedRewards);

		Map<String, Map<Month, Integer>> actualRewards = retailerServiceImpl.getCustomerRewardsByEmailId(emailId);

		assertEquals(expectedRewards, actualRewards);
	}
}
