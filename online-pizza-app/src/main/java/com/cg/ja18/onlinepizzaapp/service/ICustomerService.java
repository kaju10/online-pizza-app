package com.cg.ja18.onlinepizzaapp.service;



import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.Order;
import com.cg.ja18.onlinepizzaapp.entity.Pizza;


@Service
public interface ICustomerService {
	
	 Customer addCustomer(Customer customer);  

	 Customer updateCustomer(Customer customer);
    
	 List<Pizza> viewPizzaList();

	 List<Order> viewOrdersByCustomer(Long mobile);
	 
	 Customer viewCustomerById(Long mobile);

}