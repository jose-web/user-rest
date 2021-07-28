package com.idb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idb.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping("/create")
	public boolean create(String email, String pass, String username, String birthday) {
		return userService.create(email, pass, username, birthday);
	}

	@PostMapping("/delete")
	public boolean create(Long id) {
		return userService.delete(id);
	}
}
