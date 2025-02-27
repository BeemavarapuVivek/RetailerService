package com.cats.retailer.serviceimpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
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
	
	@Override
	public Transaction saveTransaction(Transaction transaction) {
		// TODO Auto-generated method stub
		return retailerRepository.save(transaction);
	}
	@Override
	public Map<String, Map<Month, Integer>> getAllCustomerRewards() {
		Map<String, Map<Month, Integer>> customerRewards=null;
		try {
			Calendar calendar=Calendar.getInstance();
			calendar.add(Calendar.MONTH, 3);
			Date d=calendar.getTime();
//			List<Transaction> transactionList=retailerRepository.findLastThreeMonthsTransactions(d);
			List<Transaction> transactionList=retailerRepository.findAll();
			
			if(!transactionList.isEmpty() && transactionList!=null) {
				customerRewards=CustomerUtils.calculateRewardsForCustomerTransaction(transactionList);	
			}else {
				throw new NoSuchRecordAvailable("No Such Record available::");
			}
			return customerRewards;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			throw new NoSuchRecordAvailable("No Such Record available::");
		}
	}


	

}
