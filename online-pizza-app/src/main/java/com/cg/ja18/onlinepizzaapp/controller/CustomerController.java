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

import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.Order;
import com.cg.ja18.onlinepizzaapp.service.ICustomerService;
import com.cg.ja18.onlinepizzaapp.service.IPizzaService;
import com.cg.ja18.onlinepizzaapp.entity.Pizza;

@CrossOrigin(origins = "*")
@RestController
public class CustomerController {

	@Autowired
	private ICustomerService custService;

	@Autowired
	private IPizzaService pizzaService;

//	@Autowired
//	private ILoginRepository userRepo;

	@PostMapping("/saveCustomer")
	public Customer addCustomer(@Valid @RequestBody Customer customer) {
		return custService.addCustomer(customer);
	}

	@PutMapping("/updateCustomer")
	public Customer updateCustomer(@RequestBody Customer customer) {
		return custService.updateCustomer(customer);
	}

	@GetMapping("/viewPizza")
	public List<Pizza> viewPizzaList() {
		return pizzaService.viewPizzaList();

	}

	@GetMapping("/viewOrdersOrdered/{mobile}")
	public List<Order> viewOrdersOfSingleCustomer(@PathVariable Long mobile) {

		return custService.viewOrdersByCustomer(mobile);

	}
}
