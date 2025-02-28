package com.cats.retailer.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cats.retailer.entity.Transaction;
@Repository
public interface RetailerRepository extends JpaRepository<Transaction, Long> {

	@Query("SELECT t FROM Transaction t WHERE t.creationDate >= :date")
	public  List<Transaction> findRecordsFromLastThreeMonths(LocalDate date);


}
