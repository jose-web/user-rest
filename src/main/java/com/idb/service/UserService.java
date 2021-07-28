package com.idb.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public User findById(Long id) {
		return userRepository.findById(id).get();
	}

}
