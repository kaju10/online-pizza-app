package com.cg.ja18.onlinepizzaapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ja18.onlinepizzaapp.entity.Admin;
import com.cg.ja18.onlinepizzaapp.entity.Customer;
import com.cg.ja18.onlinepizzaapp.exceptions.AdminIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.exceptions.CustomerIdNotFoundException;
import com.cg.ja18.onlinepizzaapp.repository.IAdminRepository;
import com.cg.ja18.onlinepizzaapp.repository.ICustomerRepository;

@Service
public class LoginServiceImpl implements ILoginService {

//	@Autowired
//	private ILoginRepository userRepo;

	@Autowired
	private ICustomerRepository custRepo;

	@Autowired
	private IAdminRepository adminRepo;

//	public boolean validateCustomer(Long mobile, String password) {
//		User user = userRepo.getByMobileAndPassword(mobile, password);
//		if (Objects.nonNull(user))
//			return true;
//		else
//			return false;
//	}

	@Override
	public boolean validateCustomer(Long mobile, String password) {
		// TODO Auto-generated method stub
		Optional<Customer> cust = custRepo.findById(mobile);
		if(cust.isPresent()) {
			if (cust.get().getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		}else {
				throw new CustomerIdNotFoundException("Customer is not Registered");
			}
			
	}

	@Override
	public boolean validateAdmin(Long mobile, String password) {
		// TODO Auto-generated method stub
		Optional<Admin> adm = adminRepo.findById(mobile);
		if(adm.isPresent()) {
			if (adm.get().getPassword().equals(password)) {
				return true;
			} else {
				return false;
			}
		}else {
			throw new AdminIdNotFoundException("Admin is not registered");
		}
		
	}

}
