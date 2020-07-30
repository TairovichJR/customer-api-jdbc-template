package com.tairovich.service;

import java.util.List;

import com.tairovich.entity.Customer;

public interface CustomerService {

	public List<Customer> getCustomers();

	public Customer getCustomerById(Long customerId);

	public String addCustomer(Customer customer);

	public String addCustomers(List<Customer> customers);

	public String updateCustomer(Customer customer);

	public String deleteCustomer(Long customerId);
	
}
