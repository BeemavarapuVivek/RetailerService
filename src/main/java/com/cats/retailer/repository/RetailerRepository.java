package com.cats.retailer.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cats.retailer.entity.Transaction;
@Repository
public interface RetailerRepository extends JpaRepository<Transaction, Long> {

//	 @Query("SELECT e FROM transaction_tab e WHERE creation_date >=:threemonthsAgoDate")
//	 List<Transaction> findLastThreeMonthsTransactions(Date threemonthsAgoDate );
}
