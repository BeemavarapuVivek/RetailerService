package com.cats.retailer.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.cats.retailer.entity.Transaction;

@Component
public class CustomerUtils {



	public static Map<String, Map<Month, Integer>> calculateRewardsForCustomerTransaction(
			List<Transaction> transactionList) {

		Map<String, List<Transaction>> customerWiseTransactionList = transactionList.stream()
				.collect(Collectors.groupingBy(Transaction::getCustomerEmail));

		Map<String, Map<Month, Integer>> customerWithMonthlyRewards = new HashMap<>();
		for (Map.Entry<String, List<Transaction>> mapWithCustomerInfo : customerWiseTransactionList.entrySet()) {
			Map<Month, Integer> monthlyPoints = new HashMap<>();

			for (Transaction transactionBean : mapWithCustomerInfo.getValue()) {
//				LocalDate dateTime = convertDateToLocalDate(transactionBean.getCreation_date());
				LocalDate dateTime = transactionBean.getCreationDate();
				Month month = dateTime.getMonth();
				int points = calculateRewardsForEachTransaction(transactionBean.getTransactionAmount());
				if (monthlyPoints.containsKey(month)) {
					monthlyPoints.put(month, monthlyPoints.get(month) + points);
				} else {
					monthlyPoints.put(month, points);
				}
			}
			customerWithMonthlyRewards.put(mapWithCustomerInfo.getKey(), monthlyPoints);
		}
		return customerWithMonthlyRewards;
	}

	public static int calculateRewardsForEachTransaction(double amount) {
		int points = 0;
		if (amount > 100) {
			points += (amount - 100) * 2 + 50;

		} else if (amount > 50) {
			points += (amount - 50);
		}
		return points;
	}
	
//	public static LocalDate convertDateToLocalDate(Date inputDate) {
//		LocalDate currentDate = inputDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
//		return currentDate;
//	}
	
//	public static Date convertStringDateToDate(String strDate) throws ParseException {
//
//		Date date = null;
//		try {
//			DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//			date = formatter.parse(strDate);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return date;
//	}
}
