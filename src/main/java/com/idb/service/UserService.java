package com.idb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idb.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;

}
