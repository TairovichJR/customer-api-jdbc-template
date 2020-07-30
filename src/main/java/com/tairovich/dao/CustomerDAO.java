package com.tairovich.dao;

import java.util.List;

import com.tairovich.entity.Customer;

public interface CustomerDAO {

	public String insertCustomer(Customer customer);

	public List<Customer> getCustomers();

	public Customer getCustomerById(Long customerId);

	public String insertCustomers(List<Customer> customers);

	public String updateCustomer(Customer customer);

	public String deleteCustomer(Long customerId);

}
