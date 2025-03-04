package com.cats.retailer.util;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cats.retailer.entity.Transaction;

@Component
public class CustomerUtils {

	public Map<String, Map<Month, Integer>> calculateCustomerTransactionRewards(List<Transaction> transactionList) {

		Map<String, List<Transaction>> customerTransactionMap = transactionList.stream()
				.collect(Collectors.groupingBy(Transaction::getCustomerEmail));

		Map<String, Map<Month, Integer>> customerMonthlyRewards = new HashMap<>();
		for (Map.Entry<String, List<Transaction>> customerEntry : customerTransactionMap.entrySet()) {
			Map<Month, Integer> monthlyRewards = new HashMap<>();

			for (Transaction transaction : customerEntry.getValue()) {
				LocalDate dateTime = transaction.getCreationDate();
				Month transactionMonth = dateTime.getMonth();
				int rewardPoints = calculateRewardsForEachTransaction(transaction.getTransactionAmount());
				if (monthlyRewards.containsKey(transactionMonth)) {
					monthlyRewards.put(transactionMonth, monthlyRewards.get(transactionMonth) + rewardPoints);
				} else {
					monthlyRewards.put(transactionMonth, rewardPoints);
				}
			}
			customerMonthlyRewards.put(customerEntry.getKey(), monthlyRewards);
		}
		return customerMonthlyRewards;
	}

	public int calculateRewardsForEachTransaction(double amount) {
		int points = 0;
		if (amount > 100) {
			points += (amount - 100) * 2 + 50;

		} else if (amount > 50) {
			points += (amount - 50);
		}
		return points;
	}

}
