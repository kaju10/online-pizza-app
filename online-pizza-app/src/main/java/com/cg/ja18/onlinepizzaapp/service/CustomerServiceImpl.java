package com.cg.ja18.onlinepizzaapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ja18.onlinepizzaapp.entity.Admin;
import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.Order;
import com.cg.ja18.onlinepizzaapp.entity.Pizza;
import com.cg.ja18.onlinepizzaapp.entity.User;
import com.cg.ja18.onlinepizzaapp.exceptions.AdminIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.exceptions.CustomerAlreadyPresentException;
import com.cg.ja18.onlinepizzaapp.exceptions.CustomerIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.repository.ICustomerRepository;
import com.cg.ja18.onlinepizzaapp.repository.IOrderRepository;
import com.cg.ja18.onlinepizzaapp.repository.IPizzaRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository custRepo;

	@Autowired
	private IPizzaRepository pizzaRepo;

//	@Autowired
//	private ILoginRepository userRepo;

	
	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		Optional<Customer> cust = custRepo.findById(customer.getMobile());
		if(cust.isPresent()) {
			throw new CustomerAlreadyPresentException("Customer with mobile number " + customer.getMobile() + " is already registered");
		}
		else {
			return custRepo.save(customer);
		}	
	}
//	@Override
//	public Customer addCustomer(Customer customer) {
//		// TODO Auto-generated method stub
//
//		return custRepo.save(customer);
//	}

	@Override
	public Customer updateCustomer(Customer customer) {
		// TODO Auto-generated method stub

		Optional<Customer> cust = custRepo.findById(customer.getMobile());
		
		if(cust.isPresent()) {
			return custRepo.save(customer);
		}
		else {
			return addCustomer(customer);
		}

//		if (Objects.nonNull(customer.getCustomerName()) && !"".equalsIgnoreCase(customer.getCustomerName())) {
//			cust.setCustomerName(customer.getCustomerName());
//		}
//
//			if (Objects.nonNull(customer.getCustomerMobile()) ) {
//				cust.setCustomerMobile(customer.getCustomerMobile());
//			}
//		if (Objects.nonNull(customer.getCustomerAddress()) && !"".equalsIgnoreCase(customer.getCustomerAddress())) {
//			cust.setCustomerAddress(customer.getCustomerAddress());
//		}
//		if (Objects.nonNull(customer.getCustomerEmail()) && !"".equalsIgnoreCase(customer.getCustomerEmail())) {
//			cust.setCustomerEmail(customer.getCustomerEmail());
//		}
//
//		return custRepo.save(cust);
	}

	@Override
	public List<Pizza> viewPizzaList() {
		List<Pizza> list = new ArrayList<>();
		pizzaRepo.findAll().forEach(list::add);
		return list;
	}

	@Override
	public List<Order> viewOrdersByCustomer(Long mobile) {
		// TODO Auto-generated method stub

		return custRepo.findById(mobile).get().getOrder();

	}

	@Override
	public Customer viewCustomerById(Long mobile) {
		Optional<Customer> cust = custRepo.findById(mobile);
		if (cust.isPresent()) {
			return cust.get();

		} else {
			throw new CustomerIdNotFoundException("Customer id is not available");
		}
	}

	

}