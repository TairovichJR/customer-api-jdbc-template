package com.tairovich;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.tairovich.entity.Customer;
import com.tairovich.service.CustomerService;
import com.tairovich.utils.AppUtils;

@SpringBootApplication
public class CustomerApiJdbcApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(CustomerApiJdbcApplication.class, args);
		
		CustomerService customerService = context.getBean(CustomerService.class);
		
		/* Run this line once only to generate data and persist in the DB*/
		List<Customer> customers = AppUtils.generateRandomCustomers(100);		
		customerService.addCustomers(customers);
	}
}
