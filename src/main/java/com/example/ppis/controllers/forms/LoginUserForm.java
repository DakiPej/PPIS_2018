package com.example.ppis.controllers.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class LoginUserForm {

	@Size(min = 3, max = 15)
	@NotNull
	private String username;
	
	@Size(min = 8, max = 20)
	@NotNull
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
