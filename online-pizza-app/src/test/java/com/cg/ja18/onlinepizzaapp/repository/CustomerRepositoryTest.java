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

	Customer cust= new Customer();

	@BeforeEach
	void setUp() throws Exception {

		cust.setMobile(9876543210L);
		cust.setUserName("abc");
		cust.setPassword("xyz");
		cust.setCustomerAddress("Kolkata");
		cust.setCustomerEmail("abc@def.com");
		cust.setCustomerName("abcxyz");
	}

	@Test
	void addMethodtest() {
		assertEquals(custRepo.save(cust).getCustomerName(), cust.getCustomerName());
	}

}
