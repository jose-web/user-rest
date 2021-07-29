package com.idb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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

	@DeleteMapping("/delete")
	public boolean create(Long id) {
		return userService.delete(id);
	}

	@GetMapping("/findById")
	public String findById(Long id) {
		return userService.findById(id);
	}

	@GetMapping("/findAll")
	public String findAll() {
		return userService.findAll();
	}

	@PutMapping("/edit")
	public boolean edit(Long id, String email, String pass, String username, String birthday) {
		return userService.edit(id, email, pass, username, birthday);
	}

}
