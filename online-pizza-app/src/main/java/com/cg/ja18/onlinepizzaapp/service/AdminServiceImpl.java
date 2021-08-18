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
import com.cg.ja18.onlinepizzaapp.exceptions.AdminAlreadyPresentException;
import com.cg.ja18.onlinepizzaapp.exceptions.AdminIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.exceptions.CustomerIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.exceptions.OrderIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.repository.IAdminRepository;
import com.cg.ja18.onlinepizzaapp.repository.ICustomerRepository;
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
	public Admin addAdmin(Admin admin) {
		Optional<Admin> adm = adminrepo.findById(admin.getMobile());
		if(adm.isPresent()) {
			throw new AdminAlreadyPresentException("Admin with mobile number " + admin.getMobile() + " is already registered");
		}
		else {
			return adminrepo.save(admin);
		}
	}

	@Override
	public Customer viewCustomerById(Long mobile) {
		Optional<Customer> customer = custRepo.findById(mobile);
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
	public Admin viewAdminById(Long mobile) {
		Optional<Admin> admin = adminrepo.findById(mobile);
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

	@Override
	public Admin updateAdmin(Admin admin) {
		
			// TODO Auto-generated method stub

			Optional<Admin> adm = adminrepo.findById(admin.getMobile());
			
			if(adm.isPresent()) {
				return adminrepo.save(admin);
			}
			else {
				return addAdmin(admin);
			}
		
	}

	

}