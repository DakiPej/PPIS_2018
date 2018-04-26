package com.example.ppis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.RegisteredUser;
import com.example.ppis.models.Services;
import com.example.ppis.repositories.RegisteredUserRepository;

@Repository
public class RegisteredUserDAO extends BaseDAO<RegisteredUser, RegisteredUserRepository>{
	
	public String findUser(String username, String password)	{
		String email; 
		try {
			email = this.repo.findUser(username, password);
		} catch (Exception e) {
			return "The user with the given credentials does not exist"; 
		}
		return email; 
	}
	
	public RegisteredUser findUserByUsername(String username)	{
		RegisteredUser user ; 
		try {
			user = this.repo.findByUsername(username);
		} catch (Exception e) {
			return null; 
		}
		return user; 
	}
	
	public List<Service> getAllByServices(Services service)	{
		
	}
}
