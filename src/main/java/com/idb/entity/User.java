package com.idb.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user", catalog = "test")
public class User {

	@Id
	@GeneratedValue
	private Long id;
	private String username;
	private String email;
	private String pass;
	private LocalDate birthday;

	public User(String email, String pass, String username, LocalDate birthday) {
		super();
		this.username = username;
		this.email = email;
		this.pass = pass;
		this.birthday = birthday;
	}

}
