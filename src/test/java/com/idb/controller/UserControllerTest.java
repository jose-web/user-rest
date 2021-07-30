package com.idb.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.google.gson.Gson;
import com.idb.entity.User;
import com.idb.service.UserService;

@WebMvcTest(controllers = UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	@DisplayName("Create user")
	public void create() throws Exception {
		when(userService.create("jose@jose.com", "1234", "jose", "1996-12-13")).thenReturn(true);

		String response = mockMvc
				.perform(MockMvcRequestBuilders.post("/create").param("email", "jose@jose.com").param("pass", "1234")
						.param("username", "jose").param("birthday", "1996-12-13")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertTrue(Boolean.parseBoolean(response));
	}

	@Test
	@DisplayName("Delete user")
	public void delete() throws Exception {
		when(userService.delete(2L)).thenReturn(true);

		String response = mockMvc
				.perform(MockMvcRequestBuilders.delete("/delete").param("id", "2")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertTrue(Boolean.parseBoolean(response));
	}

	@Test
	@DisplayName("findById user")
	public void findById() throws Exception {
		User user = new User("jose@jose.com", "1234", "jose", LocalDate.parse("1996-12-13"));
		Gson json = new Gson();

		when(userService.findById(2L)).thenReturn(json.toJson(user));

		String response = mockMvc
				.perform(MockMvcRequestBuilders.get("/findById").param("id", "2")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String jsonCompare = "{\"username\":\"jose\",\"email\":\"jose@jose.com\",\"pass\":\"1234\",\"birthday\":{\"year\":1996,\"month\":12,\"day\":13}}";
		assertEquals(jsonCompare, response);
	}

	@Test
	@DisplayName("findAll user")
	public void findAll() throws Exception {
		User jose = new User("jose@jose.com", "1234", "jose", LocalDate.parse("1996-12-13"));
		User maria = new User("maria@maria.com", "4321", "maria", LocalDate.parse("2020-05-05"));
		ArrayList<User> userList = new ArrayList<User>();
		userList.add(jose);
		userList.add(maria);
		Iterable<User> users = userList;

		Gson json = new Gson();

		when(userService.findAll()).thenReturn(json.toJson(users));

		String response = mockMvc
				.perform(MockMvcRequestBuilders.get("/findAll").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		String jsonCompare = "[{\"username\":\"jose\",\"email\":\"jose@jose.com\",\"pass\":\"1234\",\"birthday\":{\"year\":1996,\"month\":12,\"day\":13}},{\"username\":\"maria\",\"email\":\"maria@maria.com\",\"pass\":\"4321\",\"birthday\":{\"year\":2020,\"month\":5,\"day\":5}}]";
		assertEquals(jsonCompare, response);
	}

	@Test
	@DisplayName("edit user")
	public void edit() throws Exception {
		when(userService.edit(1L, "jose@jose.com", "1234", "jose", "1996-12-13")).thenReturn(true);

		String response = mockMvc
				.perform(MockMvcRequestBuilders.put("/edit").param("id", "1").param("email", "jose@jose.com")
						.param("pass", "1234").param("username", "jose").param("birthday", "1996-12-13")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertTrue(Boolean.parseBoolean(response));
	}

}
