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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.ja18.onlinepizzaapp.entity.Admin;
import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.User;
import com.cg.ja18.onlinepizzaapp.repository.ICustomerRepository;

@SpringBootTest
class CustomerServiceTest {

	@Autowired
	private ICustomerService custService;

	@MockBean
	private ICustomerRepository custRepo;

	Customer cust;

	@BeforeEach
	void setUp() throws Exception {

		
		cust = Customer.builder().customerName("ABC").customerAddress("Kolkata")
				.customerEmail("abc@gmail.com").build();
		Mockito.when(custRepo.save(cust)).thenReturn(cust);
	}

	@Test
	void addCustomerTest() {

		
		assertEquals(cust, custService.addCustomer(cust));

	}

}