package com.cats.retailer.controller;

import java.time.Month;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cats.retailer.entity.Transaction;
import com.cats.retailer.exception.BadRequestException;
import com.cats.retailer.exception.ResouceNotFoundException;
import com.cats.retailer.service.RetailerService;



@RestController
@RequestMapping("/api/retailer")
public class RetailerController {

	private static final Logger logger =LoggerFactory.getLogger(RetailerController.class);
	
	private RetailerService retailerSevice;
	
	
	public  RetailerController(RetailerService retailerSevice) {
		super();
		this.retailerSevice = retailerSevice;
	}

	@PostMapping("/save/transaction")
	public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction) {
		logger.trace("save::");
		if(transaction==null) {
			throw new BadRequestException("Transaction must not be null");
		}
		Transaction savedTransaction=retailerSevice.saveTransaction(transaction);
		if(savedTransaction==null) {
			throw new ResouceNotFoundException("Failed to save transaction");
		}
		return ResponseEntity.ok(savedTransaction);
	}
	
	@GetMapping("/all/transactions")
	public ResponseEntity<List<Transaction>> findAllTransactions() {
		logger.trace("find all transactions::");
		List<Transaction> listOfTransaction=retailerSevice.findAllTransactions();
		if(listOfTransaction==null || listOfTransaction.isEmpty()) {
			throw new ResouceNotFoundException("No Transaction found");
		}
		return ResponseEntity.ok(listOfTransaction);
	}
	
	@GetMapping("/all/rewards")
	public ResponseEntity<Map<String, Map<Month, Integer>>> getAllCustomerRewardPoints() {
		logger.trace("find all rewards::");
		return ResponseEntity.ok(retailerSevice.getAllCustomerRewards());
	}
	
	@GetMapping("/rewards/{emailId}")
	public ResponseEntity<Map<String, Map<Month, Integer>>> getCustomerRewardsByEmail(@PathVariable String emailId) {
		logger.trace("find customer rewards::");
		if(emailId==null || emailId.isEmpty()) {
			throw new BadRequestException("Email Id must not be null or empty");
		}
		return ResponseEntity.ok(retailerSevice.getCustomerRewardsByEmailId(emailId));
	}
	
}
