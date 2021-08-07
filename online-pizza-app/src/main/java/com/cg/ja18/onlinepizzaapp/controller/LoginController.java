package com.cg.ja18.onlinepizzaapp.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ja18.onlinepizzaapp.entity.User;
import com.cg.ja18.onlinepizzaapp.service.ILoginService;

@RestController
public class LoginController {

	@Autowired
	private ILoginService loginService;

	@GetMapping("/Login/{mobile}/{password}")
	public ResponseEntity<?> validateUser(@PathVariable("mobile") Long mobile,
			@PathVariable("password") String password) {
		boolean value = loginService.validateUser(mobile, password);
		System.out.println("inside controller" + value);
		if (value == true) {
			return ResponseEntity.ok("Logged in");
		} else
			return ResponseEntity.ok("Invalid Credentials");
	}

}
