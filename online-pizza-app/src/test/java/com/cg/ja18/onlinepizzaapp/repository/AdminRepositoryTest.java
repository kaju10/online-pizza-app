package com.cg.ja18.onlinepizzaapp.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import com.cg.ja18.onlinepizzaapp.entity.Admin;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class AdminRepositoryTest {

	@Autowired
	private IAdminRepository adminRepo;

	@BeforeEach
	void setUp() throws Exception {

		Admin admin = new Admin();
		admin.setMobile(8976543210L);
		admin.setUserName("abc");
		admin.setPassword("xyz");
		admin.setAdminAddress("Kolkata");
				

		adminRepo.save(admin);
	}

	@Test
	public void whenFindById_thenReturnAdmin() {
		assertEquals(8976543210L, adminRepo.findById(8976543210L).get().getMobile());
	}

}