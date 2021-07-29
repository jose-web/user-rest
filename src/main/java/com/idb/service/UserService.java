package com.idb.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.idb.entity.User;
import com.idb.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

	public boolean create(String email, String pass, String username, String birthday) {
		try {
			LocalDate date = LocalDate.parse(birthday);
			User user = new User(email, pass, username, date);
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean delete(Long id) {
		try {
			userRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String findById(Long id) {
		Gson jsonUsers = new Gson();
		return jsonUsers.toJson(userRepository.findById(id));
	}

	public String findAll() {

		Gson jsonUsers = new Gson();
		return jsonUsers.toJson(userRepository.findAll());
	}

	public boolean edit(Long id, String email, String pass, String username, String birthday) {
		try {
			User user = userRepository.findById(id).get();
			user.setEmail(email);
			user.setPass(pass);
			user.setUsername(username);

			LocalDate date = LocalDate.parse(birthday);

			user.setBirthday(date);

			userRepository.save(user);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
