package com.example.ppis.dao;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.RegisteredUser;
import com.example.ppis.repositories.RegisteredUserRepository;

@Repository
public class RegisteredUserDAO extends BaseDAO<RegisteredUser, RegisteredUserRepository>{
	
	public Long count() {
		return this.repo.count();
	}
	
	public String findUser(String username, String password)	{
		return this.repo.findUser(username, password);
	}
	
	public RegisteredUser findByUsername(String username) {
		return this.repo.findDistinctRegisteredUserByUsername(username);
	}
}
