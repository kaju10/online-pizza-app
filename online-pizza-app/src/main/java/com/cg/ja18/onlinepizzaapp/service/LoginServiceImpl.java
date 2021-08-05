package com.cg.ja18.onlinepizzaapp.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.ja18.onlinepizzaapp.entity.User;
import com.cg.ja18.onlinepizzaapp.repository.ILoginRepository;


@Service
public class LoginServiceImpl implements ILoginService {

	@Autowired
	private ILoginRepository userRepo;

	public boolean validateUser(Long mobile, String password) {
		User user = userRepo.getByMobileAndPassword(mobile, password);
		if (Objects.nonNull(user))
			return true;
		else
			return false;
	}

}
