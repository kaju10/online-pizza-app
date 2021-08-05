package com.cg.ja18.onlinepizzaapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cg.ja18.onlinepizzaapp.entity.User;



	@Service
	public interface ILoginService {

		public boolean validateUser(Long mobile, String password);

	}


