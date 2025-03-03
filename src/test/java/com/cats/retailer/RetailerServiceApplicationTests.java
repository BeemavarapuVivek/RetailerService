package com.cats.retailer;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cats.retailer.controller.RetailerController;
import com.cats.retailer.entity.Transaction;
import com.cats.retailer.repository.RetailerRepository;
import com.cats.retailer.service.RetailerService;
import com.cats.retailer.serviceimpl.RetailerServiceImpl;
import com.cats.retailer.util.CustomerUtils;

@ExtendWith(MockitoExtension.class)
class RetailerServiceApplicationTests {
	@Mock
    private RetailerRepository retailerRepository;

    @Mock
    private CustomerUtils customerUtils;

    @InjectMocks
    private RetailerServiceImpl retailerServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    
    
    
   @Test
   public void testSaveTransaction() {
	   Transaction transaction=new Transaction("customer1@example.com", 120, LocalDate.of(2025, Month.JANUARY, 1));
	   when(retailerRepository.save(transaction)).thenReturn(transaction);
	   
	   Transaction savedTransaction=retailerServiceImpl.saveTransaction(transaction);
	   assertEquals(transaction, savedTransaction);
	   verify(retailerRepository).save(transaction);
	   
	   
   }
   
   @Test
   public void findAllTransactions() {
	   List<Transaction> transactionList = Arrays.asList(
		          new Transaction("customer1@example.com", 120, LocalDate.of(2025, Month.JANUARY, 1)),
		          new Transaction("customer2@example.com", 200, LocalDate.of(2025, Month.FEBRUARY, 1))
		      );
	   when(retailerRepository.findAll()).thenReturn(transactionList);
	   
	   List<Transaction> actualResults=retailerServiceImpl.findAllTransactions();
	   assertEquals(transactionList,actualResults);
	   verify(retailerRepository).findAll();
   }
   
   
  @Test
  public void testGetAllCustomerRewards() {
  	LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
      List<Transaction> transactionList = Arrays.asList(
          new Transaction("customer1@example.com", 120, LocalDate.of(2025, Month.JANUARY, 1)),
          new Transaction("customer2@example.com", 200, LocalDate.of(2025, Month.FEBRUARY, 1))
      );

      Map<String, Map<Month, Integer>> expectedRewards = new HashMap<String, Map<Month, Integer>>();
      Map<Month, Integer> customer1Rewards = new HashMap<Month, Integer>();
      customer1Rewards.put(Month.JANUARY, 90); 
      expectedRewards.put("customer1@example.com", customer1Rewards);
      Map<Month, Integer> customer2Rewards = new HashMap<Month, Integer>();
      customer2Rewards.put(Month.FEBRUARY, 250); 
      expectedRewards.put("customer2@example.com", customer2Rewards);

      when(retailerRepository.findRecordsFromLastThreeMonths(threeMonthsAgo)).thenReturn(transactionList);
      when(customerUtils.calculateRewardsForCustomerTransaction(transactionList)).thenReturn(expectedRewards);
      Map<String, Map<Month, Integer>> actualRewards = retailerServiceImpl.getAllCustomerRewards();

      assertEquals(expectedRewards, actualRewards);
  }
    @Test
    public void testGetCustomerRewardsByEmail() {
        String emailId = "customer1@gmail.com";
        LocalDate threeMonthsAgo = LocalDate.now().minusMonths(3);
        List<Transaction> transactionList = Arrays.asList(
            new Transaction(emailId, 150, LocalDate.of(2025, Month.JANUARY, 1)),
            new Transaction(emailId, 200, LocalDate.of(2025, Month.FEBRUARY, 1))
        );

        // Define expected results
        Map<String, Map<Month, Integer>> expectedRewards = new HashMap<>();
        Map<Month, Integer> customer1Rewards = new HashMap<>();
        customer1Rewards.put(Month.JANUARY, 150);
        customer1Rewards.put(Month.FEBRUARY, 250);
        expectedRewards.put(emailId, customer1Rewards);

        when(retailerRepository.findCustomerRecordsFromLastThreeMonths(threeMonthsAgo, emailId)).thenReturn(transactionList);
        when(customerUtils.calculateRewardsForCustomerTransaction(transactionList)).thenReturn(expectedRewards);

        Map<String, Map<Month, Integer>> actualRewards = retailerServiceImpl.getCustomerRewardsByEmailId(emailId);

        assertEquals(expectedRewards, actualRewards);
    }
}
