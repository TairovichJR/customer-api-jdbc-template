package com.tairovich.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tairovich.dao.CustomerDAO;
import com.tairovich.entity.Customer;

@Service
public class CustomerServiceImpl implements CustomerService{

	
	@Autowired
	CustomerDAO customerDao;
	
	
	@Override
	public String addCustomer(Customer customer) {
		
		return customerDao.insertCustomer(customer);
	
	}

	@Override
	public String addCustomers(List<Customer> customers) {
		
		return customerDao.insertCustomers(customers);
	}

	@Override
	public List<Customer> getCustomers() {
		
		return customerDao.getCustomers();
	}

	@Override
	public Customer getCustomerById(Long customerId) {
		
		return customerDao.getCustomerById(customerId);
	}

	@Override
	public String updateCustomer(Customer customer) {
	
		return customerDao.updateCustomer(customer);
	}

	@Override
	public String deleteCustomer(Long customerId) {
		
		return customerDao.deleteCustomer(customerId);
	}

}
