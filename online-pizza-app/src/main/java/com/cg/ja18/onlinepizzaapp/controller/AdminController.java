package com.cg.ja18.onlinepizzaapp.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ja18.onlinepizzaapp.entity.Admin;
import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.Pizza;
import com.cg.ja18.onlinepizzaapp.service.IAdminService;
import com.cg.ja18.onlinepizzaapp.service.IPizzaService;

@CrossOrigin("*")
@RestController
public class AdminController {

	@Autowired
	private IPizzaService pizzaService;

	@Autowired
	private IAdminService adminService;

	@GetMapping("/customer/{mobile}")
	public Customer viewCustomerByCustomerId(@PathVariable("mobile") Long mobile) {
		return adminService.viewCustomerById(mobile);

	}
	
	@GetMapping("/listcustomer/{mobile}")
	public List<Customer> viewListCustomerByCustomerId(@PathVariable("mobile") Long mobile) {
		return adminService.viewListCustomerById(mobile);

	}
	
	@PutMapping("/updateAdmin")
	public Admin updateAdmin(@RequestBody Admin admin) {
		return adminService.updateAdmin(admin);
	}

	@PostMapping("/addPizza")
	public Pizza addPizza(@Valid @RequestBody Pizza pizza) {
		return pizzaService.addPizza(pizza);
	}

	@GetMapping("/viewCustomer")
	public List<Customer> viewCustomer() {
		return adminService.viewCustomer();
	}

	@PostMapping("/saveAdmin")
	public Admin addAdmin(@RequestBody Admin admin) {
		return adminService.addAdmin(admin);

	}

	@GetMapping("/viewAdmin")
	public List<Admin> viewAdmin() {
		return adminService.viewAdmin();
	}
	
	@GetMapping("/viewAdmin/{mobile}")
	public Admin viewAdminById(@PathVariable("mobile") Long mobile) {
		return adminService.viewAdminById(mobile);
	}

}
