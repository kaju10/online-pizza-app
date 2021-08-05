package com.cg.ja18.onlinepizzaapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.service.ICustomerService;
import com.cg.ja18.onlinepizzaapp.service.IPizzaService;
import com.cg.ja18.onlinepizzaapp.entity.Pizza;
import com.cg.ja18.onlinepizzaapp.entity.User;
import com.cg.ja18.onlinepizzaapp.repository.ILoginRepository;

@RestController
public class CustomerController {

	

	@Autowired
	private ICustomerService custService;
	
	@Autowired
	private IPizzaService pizzaService;

	@Autowired
	private ILoginRepository userRepo;
		
		@PostMapping("/saveCustomer")
		public Customer addCustomer(@Valid @RequestBody Customer customer) {
			for(User user : userRepo.findAll()) {
				if(customer.getUser().getMobile()==user.getMobile()) {
					System.out.print("user already registered.");
				}
			}
			return custService.addCustomer(customer);
		}
	

	/*
	 * @GetMapping("/customer") public List<Customer> viewCustomers() {
	 * 
	 * return custService.viewCustomers(); }
	 */
	/*
	 * @GetMapping("/customer/{id}") public Customer
	 * viewCustomer(@PathVariable("id")Integer customerId) { return
	 * custService.viewCustomer(customerId);
	 * 
	 * }
	 * 
	 * @DeleteMapping("/customer/{id}") public String
	 * deleteCustomer(@PathVariable("id") Integer customerId) {
	 * custService.deleteCustomer(customerId); return "Customer - " + customerId +
	 * " deleted successfully"; }
	 */

	@PutMapping("/updateCustomer/{customerId}")
	public Customer updateCustomer(@PathVariable("customerId") Long customerId, @RequestBody Customer customer) {
		return custService.updateCustomer(customerId, customer);
	}

	@GetMapping("/viewPizza")
	public List<Pizza> viewPizzaList(){
		return pizzaService.viewPizzaList();
		
	}
}
