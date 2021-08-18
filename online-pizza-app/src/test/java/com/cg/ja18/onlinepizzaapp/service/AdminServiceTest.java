package com.cg.ja18.onlinepizzaapp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

import com.cg.ja18.onlinepizzaapp.entity.Admin;
import com.cg.ja18.onlinepizzaapp.repository.IAdminRepository;

@SpringBootTest
class AdminServiceTest {

	@Autowired
	private IAdminService adminService;

	@MockBean
	private IAdminRepository adminRepo;
	
	Admin admin = new Admin();

	@BeforeEach
	void setUp() throws Exception {

		
		admin.setMobile(8976543210L);
		admin.setUserName("abc");
		admin.setPassword("xyz");
		admin.setAdminAddress("Kolkata");

		Mockito.when(adminRepo.findById(8976543210L)).thenReturn(Optional.of(admin));

	}

	@Test
	@DisplayName("testing the Admin by Id")
	void fetchAdminByIdTest() {

		
		assertEquals(admin, adminService.viewAdminById(8976543210L));

	}

}