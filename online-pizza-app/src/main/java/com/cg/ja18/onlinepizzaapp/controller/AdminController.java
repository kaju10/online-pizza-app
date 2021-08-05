package com.cg.ja18.onlinepizzaapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ja18.onlinepizzaapp.entity.Admin;
import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.Pizza;
import com.cg.ja18.onlinepizzaapp.service.IAdminService;
import com.cg.ja18.onlinepizzaapp.service.IPizzaService;

@RestController
public class AdminController {
	
	@Autowired
	private IPizzaService pizzaService;
	
	@Autowired
	private IAdminService adminService;
	
	@GetMapping("/customer/{customerId}")
	public Customer viewCustomerByCustomerId(@PathVariable("customerId") Long customerId) {
		return adminService.viewCustomerById(customerId);

	}
	
	@PostMapping("/addPizza")
	public Pizza addPizza(@Valid @RequestBody Pizza pizza) {
		return pizzaService.addPizza(pizza);
	}

	@GetMapping("/viewCustomer")
	public List<Customer> viewCustomer(){
		return adminService.viewCustomer();
	}
	
	@PostMapping("/saveAdmin")
	public Admin addAdmin(@RequestBody Admin admin) {
		return adminService.addAdmin(admin);
		
	}
	
	@GetMapping("/viewAdmin")
	public List<Admin> viewAdmin(){
		return adminService.viewAdmin();	
	}
	
}

