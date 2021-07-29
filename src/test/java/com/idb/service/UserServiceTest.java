package com.idb.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.google.gson.Gson;
import com.idb.entity.User;
import com.idb.repository.UserRepository;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

	@InjectMocks
	UserService userService;

	@Mock
	UserRepository userRepository;

	@Test
	@DisplayName("Create user")
	public void create() {
		boolean response = userService.create("jose@jose.com", "1234", "jose", "1996-12-13");
		assertTrue(response);
	}

	@Test
	@DisplayName("Edit user")
	public void edit() {

		User user = new User();
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

		boolean response = userService.edit(1L, "jose@jose.com", "1234", "jose", "1996-12-13");
		assertTrue(response);
	}

	@Test
	@DisplayName("find user")
	public void find() {

		User user = new User("jose@jose.com", "1234", "jose", LocalDate.parse("1996-12-13"));
		Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));

		Gson json = new Gson();
		String response = json.toJson(userService.findById(1L));

		String jsonCompare = "{\"value\":{\"username\":\"jose\",\"email\":\"jose@jose.com\",\"pass\":\"1234\",\"birthday\":{\"year\":1996,\"month\":12,\"day\":13}}}";
		String responseCompare = json.toJson(jsonCompare);

		assertEquals(responseCompare, response);
	}

	@Test
	@DisplayName("find all user")
	public void findAll() {

		User jose = new User("jose@jose.com", "1234", "jose", LocalDate.parse("1996-12-13"));
		User maria = new User("maria@maria.com", "4321", "maria", LocalDate.parse("2020-05-05"));
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(jose);
		userList.add(maria);
		Iterable<User> users = userList;

		Mockito.when(userRepository.findAll()).thenReturn(users);

		Gson json = new Gson();
		String response = json.toJson(userService.findAll());

		String jsonCompare = "[{\"username\":\"jose\",\"email\":\"jose@jose.com\",\"pass\":\"1234\",\"birthday\":{\"year\":1996,\"month\":12,\"day\":13}},{\"username\":\"maria\",\"email\":\"maria@maria.com\",\"pass\":\"4321\",\"birthday\":{\"year\":2020,\"month\":5,\"day\":5}}]";
		String responseCompare = json.toJson(jsonCompare);

		assertEquals(responseCompare, response);
	}

	@Test
	@DisplayName("delete user")
	public void delete() {
		boolean response = userService.delete(100L);
		assertTrue(response);
	}
}
