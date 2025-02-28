package com.cats.retailer.controller;

import java.time.Month;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cats.retailer.entity.Transaction;
import com.cats.retailer.service.RetailerService;

@RestController
@RequestMapping("/api/retailer")
public class RetailerController {

	@Autowired
	private RetailerService retailerSevice;
	
	@PostMapping("/save/transaction")
	public ResponseEntity<Transaction> saveTransaction(@RequestBody Transaction transaction) {
		return ResponseEntity.ok(retailerSevice.saveTransaction(transaction));
	}
	
	@GetMapping("/all/transactions")
	public ResponseEntity<List<Transaction>> findAllTransactions() {
		return ResponseEntity.ok(retailerSevice.findAllTransactions());
	}
	
	@GetMapping("/all/rewards")
	public ResponseEntity<Map<String, Map<Month, Integer>>> getAllCustomerRewardPoints() {
		return ResponseEntity.ok(retailerSevice.getAllCustomerRewards());
	}
	
	
}
