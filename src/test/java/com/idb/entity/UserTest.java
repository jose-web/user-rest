package com.idb.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.idb.repository.UserRepository;

public class UserTest {

	@Mock
	UserRepository userRepository;

	User user = new User();

	@Test
	@DisplayName("Setters and getters")
	public void SettersGetters() {
		user.setId(2L);
		user.setEmail("jose@jose.com");
		user.setPass("1234");
		user.setUsername("jose");
		user.setBirthday(LocalDate.parse("1996-12-13"));

		assertEquals(2L, user.getId());
		assertEquals("jose@jose.com", user.getEmail());
		assertEquals("1234", user.getPass());
		assertEquals("jose", user.getUsername());
		assertEquals(LocalDate.parse("1996-12-13"), user.getBirthday());

	}

	@Test
	public void Getters() {
		User user = new User("jose@jose.com", "1234", "jose", LocalDate.parse("1996-12-13"));
		User user2 = new User("jose@jose.com", "1234", "jose", LocalDate.parse("1996-12-13"));

		assertTrue(user.equals(user2));
		assertTrue(user.canEqual(user2));
		assertEquals(user.hashCode(), user2.hashCode());
	}
}
