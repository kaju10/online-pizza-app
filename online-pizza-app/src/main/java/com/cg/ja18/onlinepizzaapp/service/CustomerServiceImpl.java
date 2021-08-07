package com.cg.ja18.onlinepizzaapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.Order;
import com.cg.ja18.onlinepizzaapp.entity.Pizza;
import com.cg.ja18.onlinepizzaapp.entity.User;
import com.cg.ja18.onlinepizzaapp.repository.ICustomerRepository;
import com.cg.ja18.onlinepizzaapp.repository.ILoginRepository;
import com.cg.ja18.onlinepizzaapp.repository.IOrderRepository;
import com.cg.ja18.onlinepizzaapp.repository.IPizzaRepository;

@Service
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	private ICustomerRepository custRepo;

	@Autowired
	private IPizzaRepository pizzaRepo;

	@Autowired
	private ILoginRepository userRepo;

	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub

		return custRepo.save(customer);
	}

	@Override
	public Customer updateCustomer(Long customerId, Customer customer) {
		// TODO Auto-generated method stub

		Customer cust = custRepo.findById(customerId).get();

		if (Objects.nonNull(customer.getCustomerName()) && !"".equalsIgnoreCase(customer.getCustomerName())) {
			cust.setCustomerName(customer.getCustomerName());
		}

//			if (Objects.nonNull(customer.getCustomerMobile()) ) {
//				cust.setCustomerMobile(customer.getCustomerMobile());
//			}
		if (Objects.nonNull(customer.getCustomerAddress()) && !"".equalsIgnoreCase(customer.getCustomerAddress())) {
			cust.setCustomerAddress(customer.getCustomerAddress());
		}
		if (Objects.nonNull(customer.getCustomerEmail()) && !"".equalsIgnoreCase(customer.getCustomerEmail())) {
			cust.setCustomerEmail(customer.getCustomerEmail());
		}

		return custRepo.save(cust);
	}

	@Override
	public List<Pizza> viewPizzaList() {
		List<Pizza> list = new ArrayList<>();
		pizzaRepo.findAll().forEach(list::add);
		return list;
	}

	@Override
	public List<Order> viewOrdersByCustomer(Long customerId) {
		// TODO Auto-generated method stub

		return custRepo.findById(customerId).get().getOrder();

	}

}