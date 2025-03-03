package com.cats.retailer.serviceimpl;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cats.retailer.entity.Transaction;
import com.cats.retailer.exception.NoSuchRecordAvailable;
import com.cats.retailer.repository.RetailerRepository;
import com.cats.retailer.service.RetailerService;
import com.cats.retailer.util.CustomerUtils;
@Service
public class RetailerServiceImpl implements RetailerService {

	@Autowired
	private RetailerRepository retailerRepository;
	
	@Autowired
	private CustomerUtils customerUtils;
	
	
	@Override
	public Transaction saveTransaction(Transaction transaction) {
		return retailerRepository.save(transaction);
	}
	
	@Override
	public List<Transaction> findAllTransactions() {

		return retailerRepository.findAll();
	}
	
	@Override
	public Map<String, Map<Month, Integer>> getAllCustomerRewards() {
		Map<String, Map<Month, Integer>> allCustomerRewards=null;
		try {
			LocalDate localDate=LocalDate.now().minusMonths(3);
			List<Transaction> transactionList=retailerRepository.findRecordsFromLastThreeMonths(localDate);
			
			if(!transactionList.isEmpty() && transactionList!=null) {
				allCustomerRewards=customerUtils.calculateRewardsForCustomerTransaction(transactionList);	
			}else {
				throw new NoSuchRecordAvailable("No Such Record available::");
			}
			return allCustomerRewards;
		}catch(Exception e) {
			throw new NoSuchRecordAvailable("No Such Record available::");
		}
	}

	@Override
	public Map<String, Map<Month, Integer>> getCustomerRewardsByEmailId(String emailId) {
		Map<String, Map<Month, Integer>> customerRewards=null;
		try {
			LocalDate localDate=LocalDate.now().minusMonths(3);
			List<Transaction> transactionList=retailerRepository.findCustomerRecordsFromLastThreeMonths(localDate,emailId);
			
			if(!transactionList.isEmpty() && transactionList!=null) {
				customerRewards=customerUtils.calculateRewardsForCustomerTransaction(transactionList);	
			}else {
				throw new NoSuchRecordAvailable("No Such Record available::");
			}
			return customerRewards;
		}catch(Exception e) {
			throw new NoSuchRecordAvailable("No Such Record available::");
		}
	}



	


	

}
