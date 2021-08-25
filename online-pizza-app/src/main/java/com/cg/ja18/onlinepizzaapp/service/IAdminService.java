package com.cg.ja18.onlinepizzaapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.ja18.onlinepizzaapp.entity.Admin;
import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.Pizza;

@Service
public interface IAdminService {

	Customer viewCustomerById(Long mobile);

	Pizza addPizza(Pizza pizza);

	List<Customer> viewCustomer();

	Admin addAdmin(Admin admin);
	
	Admin updateAdmin(Admin admin);

	List<Admin> viewAdmin();

	Admin viewAdminById(Long mobile);
	
	List<Customer> viewListCustomerById(Long mobile);

}
