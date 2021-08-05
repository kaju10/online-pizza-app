package com.cg.ja18.onlinepizzaapp.service;


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
	private IAdminService adminService ;
	
	@MockBean
	private IAdminRepository adminRepo ;
	
	@BeforeEach
	void setUp() throws Exception {
		
		
	  Admin admin1=
	  Admin.builder().adminId(2000L).adminName("Aakash").adminpassword("Sky584%").build();
	 
		
		Mockito.when(adminRepo.findByAdminId(2000L)).thenReturn(admin1);

		
		
	}
	@Test
	@DisplayName("testing the Admin by Id")
	void fetchAdminByIdTest() {
		
		Long adminId = 2000L;
		
		Admin found = adminService.viewAdminById(adminId);
		
		assertEquals(adminId, found.getAdminId());

}
	
	
}
