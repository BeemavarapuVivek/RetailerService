package com.cats.retailer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.cats.retailer.controller.RetailerController;

@SpringBootApplication
public class RetailerServiceApplication {
	private static final Logger logger =LoggerFactory.getLogger(RetailerServiceApplication.class);
	public static void main(String[] args) {
		
		SpringApplication.run(RetailerServiceApplication.class, args);
		logger.trace("Application gets started::");
	}

}
