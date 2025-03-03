package com.cats.retailer.entity;







import java.time.LocalDate;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;


@Entity
@Table(name="transaction")
public class Transaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="cust_email")
	private String customerEmail;
	
	@Column(name="tran_amount")
	private double transactionAmount;

	
	@Temporal(TemporalType.DATE)
	@Column(name="creation_date")
	private LocalDate creationDate;

	public Transaction() {
		super();
	}
	public Transaction(String customerEmail, double transactionAmount, LocalDate creationDate) {
		super();
//		this.id = id;
		this.customerEmail = customerEmail;
		this.transactionAmount = transactionAmount;
		this.creationDate = creationDate;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getCustomerEmail() {
		return customerEmail;
	}


	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}


	public double getTransactionAmount() {
		return transactionAmount;
	}


	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}


	public LocalDate getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}

	


	
    

}
