package com.tairovich.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.tairovich.entity.Customer;

@Repository
public class CustomerDAOImpl extends JdbcDaoSupport implements CustomerDAO {

	@Autowired
	DataSource dataSource;

	@PostConstruct
	public void initialize() {
		setDataSource(dataSource);
	}

	@Override
	public List<Customer> getCustomers() {

		String query = "SELECT * FROM CUSTOMER ORDER BY CUSTOMER_ID";
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList(query);
		List<Customer> customers = new ArrayList<>();
		for (Map<String, Object> row : rows) {
			Customer customer = new Customer();

			customer.setCustomerId(Long.parseLong(row.get("CUSTOMER_ID").toString()));
			customer.setFirstName(row.get("FIRST_NAME").toString());
			customer.setLastName(row.get("LAST_NAME").toString());
			customer.setEmailId(row.get("EMAIL_ID").toString());
			customer.setPhoneNumber(Long.parseLong(row.get("PHONE_NUMBER").toString()));

			customers.add(customer);
		}
		return customers;
	}

	@Override
	public Customer getCustomerById(Long customerId) {

		String query = "SELECT * FROM CUSTOMER WHERE CUSTOMER_ID = ?";

		Customer cust = (Customer) getJdbcTemplate().queryForObject(query, new Object[] { customerId },
				new RowMapper<Customer>() {

					@Override
					public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
						Customer customer = new Customer();

						customer.setCustomerId(rs.getLong("CUSTOMER_ID"));
						customer.setFirstName(rs.getString("FIRST_NAME"));
						customer.setLastName(rs.getString("LAST_NAME"));
						customer.setEmailId(rs.getString("EMAIL_ID"));
						customer.setPhoneNumber(rs.getLong("PHONE_NUMBER"));

						return customer;
					}
				});
		return cust;
	}

	@Override
	public String insertCustomer(Customer customer) {
		String query = "INSERT INTO CUSTOMER (CUSTOMER_ID, FIRST_NAME, LAST_NAME, EMAIL_ID, PHONE_NUMBER) "
				+ "VALUES (?, ?, ?, ?, ?)";
		getJdbcTemplate().update(query, new Object[] { customer.getCustomerId(), customer.getFirstName(),
				customer.getLastName(), customer.getEmailId(), customer.getPhoneNumber() });

		return "Customer inserted successfully";
	}

	@Override
	public String insertCustomers(List<Customer> customers) {

		String query = "INSERT INTO CUSTOMER (CUSTOMER_ID, FIRST_NAME, LAST_NAME, EMAIL_ID, PHONE_NUMBER) "
				+ "VALUES (?, ?, ?, ?, ?)";
		getJdbcTemplate().batchUpdate(query, new BatchPreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Customer customer = customers.get(i);
				System.out.println("Cursor i is " + i);
				ps.setLong(1, customer.getCustomerId());
				ps.setString(2, customer.getFirstName());
				ps.setString(3, customer.getLastName());
				ps.setString(4, customer.getEmailId());
				ps.setLong(5, customer.getPhoneNumber());
			}

			@Override
			public int getBatchSize() {
				return customers.size();
			}
		});

		return customers.size() + " customers inserted successfully";
	}

	@Override
	public String updateCustomer(Customer customer) {

		String query = "UPDATE CUSTOMER SET FIRST_NAME = ? , LAST_NAME = ?, EMAIL_ID = ?, PHONE_NUMBER = ? WHERE CUSTOMER_ID = ?";

		int cust = getJdbcTemplate().update(query, 
											customer.getFirstName(), 
											customer.getLastName(),
											customer.getEmailId(), 
											customer.getPhoneNumber(), 
											customer.getCustomerId());
		return cust + " rows successfully updated";
	}

	@Override
	public String deleteCustomer(Long customerId) {

		String query = "DELETE FROM CUSTOMER WHERE CUSTOMER_ID = ?";
		int row = getJdbcTemplate().update(query, customerId);
		return row + " has been deleted successfully";
	}

}
