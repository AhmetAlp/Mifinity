package com.mifinity.card.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;

import com.mifinity.card.exception.MifinityException;
import com.mifinity.card.model.User;
import com.mifinity.card.service.SessionService;
import com.mifinity.card.service.UserService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired 
	UserService userService;
	@Autowired 
	SessionService sessionService;
	
	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/register")
	public User createUser(@Valid @RequestBody User user) throws MifinityException {
		return userService.createUser(user);
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/login")
	public void loginUser(@Valid @RequestBody User user) throws MifinityException {
		userService.loginUser(user.getUsername(), user.getPassword());
	}

	@CrossOrigin(origins = "*", allowedHeaders = "*")
	@PostMapping("/logout")
	public void logoutUser() {
		userService.logoutUser();
	}	
	
}
