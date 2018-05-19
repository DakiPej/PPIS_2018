package com.example.ppis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.Department;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.models.Services;
import com.example.ppis.models.UserType;
import com.example.ppis.repositories.RegisteredUserRepository;

@Repository
public class RegisteredUserDAO extends BaseDAO<RegisteredUser, RegisteredUserRepository>{
	
	public boolean existsByUsername(String username)	{
		return this.repo.existsByUsername(username) ; 
	}
	
	public Long count() {
		return this.repo.count();
	}
	
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
			throw e;  
		}
		return user; 
	}
	
	public List<Services> getAllServices(String username)	{
		List<Services> services ; 
		try {
			services = this.repo.findByUsername(username).getServices();
		} catch (Exception e) {
			throw e; 
		}
		return services; 
	}
	
	public List<RegisteredUser> getUsersByDepartment(UserType userType, Department department)	{
		List<RegisteredUser> users ; 
		try {
			users = this.repo.getUsersByDepartment(userType, department) ; 
		} catch (Exception e) {
			throw e ; 
		}
		
		return users ; 
	}
	
	public Department getUserDepartment(String username, UserType userType)	{
		try {
			return this.repo.getUserDepartment(username, userType) ; 
		} catch (Exception e) {
			throw e ; 
		}
	}
	
}
