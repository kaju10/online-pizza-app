package com.cg.ja18.onlinepizzaapp.service;

import org.springframework.stereotype.Service;

@Service
public interface ILoginService {

	public boolean validateCustomer(Long mobile, String password);
	
	public boolean validateAdmin(Long mobile, String password);
}
