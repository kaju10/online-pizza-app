package com.cg.ja18.onlinepizzaapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.ja18.onlinepizzaapp.entity.Admin;
import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.Pizza;

@Service
public interface IAdminService {
	
	public Customer viewCustomerById(Long customerId);

	public Pizza addPizza(Pizza pizza);

	public List<Customer> viewCustomer();
	
	Admin addAdmin(Admin admin);

	List<Admin> viewAdmin();

	Admin viewAdminById(Long adminId);

}

