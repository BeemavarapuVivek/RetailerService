package com.cats.retailer.serviceimpl;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cats.retailer.entity.Transaction;
import com.cats.retailer.exception.ResourceNotFoundException;
import com.cats.retailer.repository.RetailerRepository;
import com.cats.retailer.service.RetailerService;
import com.cats.retailer.util.CustomerUtils;
@Service
public class RetailerServiceImpl implements RetailerService {
	private static final Logger logger = LoggerFactory.getLogger(RetailerServiceImpl.class);
	private RetailerRepository retailerRepository;

	private CustomerUtils customerUtils;

	public RetailerServiceImpl(RetailerRepository retailerRepository, CustomerUtils customerUtils) {
		super();
		this.retailerRepository = retailerRepository;
		this.customerUtils = customerUtils;
	}

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		return retailerRepository.save(transaction);
	}

	@Override
	public List<Transaction> findAllTransactions() {

		return retailerRepository.findAll();
	}

	@Override
	public Map<String, Map<Month, Integer>> getAllCustomerRewards() throws ResourceNotFoundException, Exception {
		Map<String, Map<Month, Integer>> allCustomerRewards = null;
		try {
			LocalDate localDate = LocalDate.now().minusMonths(3);
			List<Transaction> transactionList = retailerRepository.findRecordsFromLastThreeMonths(localDate);

			if (transactionList == null || transactionList.isEmpty()) {
				throw new ResourceNotFoundException("No Rewards found");

			} else {
				allCustomerRewards = customerUtils.calculateCustomerTransactionRewards(transactionList);
			}
			return allCustomerRewards;
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResourceNotFoundException(ex.getMessage());
		} catch (Exception e) {
			logger.trace(e.getMessage());
			throw e;
		}
	}

	@Override
	public Map<String, Map<Month, Integer>> getCustomerRewardsByEmailId(String emailId)
			throws ResourceNotFoundException, Exception {
		Map<String, Map<Month, Integer>> customerRewards = null;
		try {
			LocalDate localDate = LocalDate.now().minusMonths(3);
			List<Transaction> transactionList = retailerRepository.findCustomerRecordsFromLastThreeMonths(localDate,
					emailId);

			if (transactionList == null || transactionList.isEmpty()) {
				throw new ResourceNotFoundException("No Rewards found for email Id: " + emailId);

			} else {
				customerRewards = customerUtils.calculateCustomerTransactionRewards(transactionList);
			}
			return customerRewards;
		} catch (ResourceNotFoundException ex) {
			logger.error(ex.getMessage());
			throw new ResourceNotFoundException(ex.getMessage());
		} catch (Exception e) {
			logger.trace(e.getMessage());
			throw e;
		}
	}

}
