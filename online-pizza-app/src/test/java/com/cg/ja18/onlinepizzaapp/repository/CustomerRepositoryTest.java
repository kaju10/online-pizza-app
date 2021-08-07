package com.cg.ja18.onlinepizzaapp.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.User;

@DataJpaTest
class CustomerRepositoryTest {

	@Autowired
	private ICustomerRepository custRepo;

	Customer cust;

	@BeforeEach
	void setUp() throws Exception {

		cust = Customer.builder().customerId(1L).customerName("ABC").customerAddress("Kolkata")
				.customerEmail("abc@gmail.com").userName("abc60").password("abc@60").build();
	}

	@Test
	void addMethodtest() {
		assertEquals(custRepo.save(cust), cust);
	}

}
