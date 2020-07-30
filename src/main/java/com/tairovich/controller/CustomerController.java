package com.tairovich.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tairovich.entity.Customer;
import com.tairovich.service.CustomerService;

@RestController
public class CustomerController {

	
	@Autowired
	CustomerService customerService;
	
	//http://localhost:8088/customers
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public List<Customer> getAllCustomers(){
		return customerService.getCustomers();
	}
	
	//http://localhost:8088/customers/id
	@RequestMapping(value="/customers/{customerId}", method=RequestMethod.GET)
	public Customer getCustomer(@PathVariable("customerId") Long customerId ){
		return customerService.getCustomerById(customerId);
	}
	
	//http://localhost:8088/customers/addCustomer
	@RequestMapping(value="/customers/addCustomer", method=RequestMethod.POST)
	public String adddCustomer(@RequestBody Customer customer ){
		return customerService.addCustomer(customer);
	}
	
	//http://localhost:8088/customers/updateCustomer
	@RequestMapping(value="/customers/updateCustomer", method=RequestMethod.PUT)
	public String updateCustomer(@RequestBody Customer customer ){
		return customerService.updateCustomer(customer);
	}
	
	//http://localhost:8088/customers/deleteCustomer/{id}
	@RequestMapping(value="/customers/deleteCustomer/{customerId}", method=RequestMethod.DELETE)
	public String deleteCustomer(@PathVariable("customerId") Long customerId) {
		return customerService.deleteCustomer(customerId);
	}
	
}
