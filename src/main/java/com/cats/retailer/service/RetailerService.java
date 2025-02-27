package com.cats.retailer.service;

import java.time.Month;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.cats.retailer.entity.Transaction;

@Service
public interface RetailerService {
	
	public Transaction saveTransaction(Transaction transaction);

	public Map<String, Map<Month, Integer>> getAllCustomerRewards();
}
