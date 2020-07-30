package com.tairovich.utils;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.github.javafaker.Faker;
import com.tairovich.entity.Customer;

public class AppUtils {

	private static Faker faker = new Faker();
	
	public static String genrateRandomFirstName() {
		return faker.name().firstName();
	}
	
	public static String genrateRandomLastName() {
		return faker.name().lastName();
	}
	
	public static long generatePhoneNumber() {
		String num ="";
		for(int i =1; i <= 10; i++) {
			num += String.valueOf(faker.number().numberBetween(1, 10));
		}
		return Long.parseLong(num);
	}
	
	public static String generateEmailId(String firstName, String lastName) {
		String[] email = {"gmail.com","yahoo.com","mail.ru","inbox.ru","myspace.com"};
		Random random = new Random();
		return firstName.toLowerCase() + "." + lastName.toLowerCase() +"@" + email[random.nextInt(email.length)];
	}
	
	public static List<Customer> generateRandomCustomers(int count){
		
		List<Customer> customers = new ArrayList<>();
		for(int i =1; i <= count; i++) {
			
			long customerId = i;
			String firstName = AppUtils.genrateRandomFirstName();
			String lastName = AppUtils.genrateRandomLastName();
			String emailId = AppUtils.generateEmailId(firstName, lastName);
			long phoneNumber = AppUtils.generatePhoneNumber();
			
			Customer customer = new Customer(customerId, firstName, lastName, emailId, phoneNumber);
			customers.add(customer);
		}
		return customers;
	}

}
