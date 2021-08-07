package com.cg.ja18.onlinepizzaapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ja18.onlinepizzaapp.entity.Admin;
import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.entity.Order;
import com.cg.ja18.onlinepizzaapp.entity.Pizza;
import com.cg.ja18.onlinepizzaapp.entity.User;
import com.cg.ja18.onlinepizzaapp.exceptions.AdminIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.exceptions.CustomerIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.exceptions.OrderIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.repository.IAdminRepository;
import com.cg.ja18.onlinepizzaapp.repository.ICustomerRepository;
import com.cg.ja18.onlinepizzaapp.repository.ILoginRepository;
import com.cg.ja18.onlinepizzaapp.repository.IPizzaRepository;

@Service
public class AdminServiceImpl implements IAdminService {

	@Autowired
	public ICustomerRepository custRepo;

	@Autowired
	public IPizzaRepository pizzaRepo;

	@Autowired
	public IAdminRepository adminrepo;

	@Override
	public Customer viewCustomerById(Long customerId) {
		Optional<Customer> customer = custRepo.findById(customerId);
		if (!customer.isPresent()) {
			throw new CustomerIdNotFoundException("customer id is not available");
		}
		return customer.get();
	}

	@Override
	public Pizza addPizza(Pizza pizza) {
		return pizzaRepo.save(pizza);
	}

	@Override
	public List<Customer> viewCustomer() {
		return custRepo.findAll();
	}

	@Override
	public Admin addAdmin(Admin admin) {

		return adminrepo.save(admin);

	}

	@Override
	public Admin viewAdminById(Long adminId) {
		Optional<Admin> admin = adminrepo.findById(adminId);
		if (admin.isPresent()) {
			return admin.get();

		} else {
			throw new AdminIdNotFoundException("Admin id is not available");
		}
	}

	@Override
	public List<Admin> viewAdmin() {
		return adminrepo.findAll();
	}

}