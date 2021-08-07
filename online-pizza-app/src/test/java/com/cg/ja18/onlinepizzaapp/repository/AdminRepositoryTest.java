package com.cg.ja18.onlinepizzaapp.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ContextConfiguration;

import com.cg.ja18.onlinepizzaapp.entity.Admin;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class AdminRepositoryTest {

	@Autowired
	private IAdminRepository adminRepo;

	@BeforeEach
	void setUp() throws Exception {

		Admin admin = Admin.builder().adminId(1L).adminName("Joy Roy").adminpassword("jroy456").build();

		adminRepo.save(admin);
	}

	@Test
	public void whenFindById_thenReturnAdmin() {
		assertEquals(1L, adminRepo.findById(1L).get().getAdminId());
	}

}