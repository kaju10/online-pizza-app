package com.cg.ja18.onlinepizzaapp.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.ja18.onlinepizzaapp.entity.Admin;
import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.User;
import com.cg.ja18.onlinepizzaapp.repository.ICustomerRepository;
import com.cg.ja18.onlinepizzaapp.repository.ILoginRepository;

//@ExtendWith(SpringExtension.class)
class CustomerServiceTest {

	@Autowired
	private ICustomerService custService;

	@MockBean
	private ICustomerRepository custRepo;
	

	Customer cust;
			
	@BeforeEach
	void setUp() throws Exception {

		User uss = new User(1L, "abc60", "abc@60", "customer");
		 cust = Customer.builder().
				customerId(1L).
				customerName("ABC").
				customerAddress("Kolkata").
				customerEmail("abc@gmail.com").
				userName("abc60").
				password("abc@60").
				user(uss).
				build();
		
	}

	 @Test
	 
	 @Order(1) 
	 void addCustomerTest() {
	 
	 //User uss = new User(1L, "abc60", "abc@60", "customer"); 
	 //Customer cust = new Customer(1L, "ABC", "abc@gmail.com", "Kolkata", "abc60", "abc@60", uss);
		 
	  Mockito.when(custRepo.save(cust)).thenReturn(cust); 
	  assertEquals(cust,custService.addCustomer(cust));
	 
	 }
	
	
	/*
	 * @Test
	 * 
	 * @Order(2) void updateCustomerTest() {
	 * 
	 * Optional<Customer> cust1 = Optional.ofNullable(Customer.builder().
	 * customerId(1L). customerName("ABC"). customerAddress("Kolkata").
	 * customerEmail("abc@gmail.com"). userName("abc60"). password("abc@60") .
	 * build());
	 * 
	 * Customer c = cust1.get(); c.setCustomerAddress("Delhi"); custRepo.save(c);
	 * 
	 * Mockito.when(custRepo.findById(1L)).thenReturn(cust1);
	 * Assertions.assertAll(c.getCustomerAddress()); }
	 */
}