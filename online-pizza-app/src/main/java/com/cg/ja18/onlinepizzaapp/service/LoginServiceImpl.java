package com.cg.ja18.onlinepizzaapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ja18.onlinepizzaapp.entity.Admin;
import com.cg.ja18.onlinepizzaapp.entity.Customer;
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
		Customer cust = custRepo.findById(mobile).get();
		if (cust.getPassword() == password) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean validateAdmin(Long mobile, String password) {
		// TODO Auto-generated method stub
		Admin adm = adminRepo.findById(mobile).get();
		if (adm.getPassword() == password) {
			return true;
		} else {
			return false;
		}
	}

}
