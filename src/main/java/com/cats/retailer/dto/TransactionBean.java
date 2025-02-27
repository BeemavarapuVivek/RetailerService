package com.cats.retailer.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class TransactionBean implements Serializable{

	private String email;
	
	private double amount;

	private LocalDate date;
	
	public TransactionBean(String email, double amount, LocalDate date) {
		super();
		this.email = email;
		this.date = date;
		this.amount = amount;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
