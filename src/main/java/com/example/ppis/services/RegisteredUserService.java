package com.example.ppis.services;

import javax.servlet.ServletException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppis.controllers.forms.LoginUserForm;
import com.example.ppis.dao.RegisteredUserDAO;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.repositories.RegisteredUserRepository;

@Service
public class RegisteredUserService {
	
	RegisteredUserDAO registeredUserDao; 
	
	@Autowired
	public void setRegisteredUserDAO (RegisteredUserDAO registeredUserDao)	{
		this.registeredUserDao = registeredUserDao;
	}

	public String login(LoginUserForm loginUser) throws ServletException{
		if(loginUser.getUsername() == null || loginUser.getUsername().isEmpty())
			throw new ServletException("Username polje ne smije biti prazno.");
		if(loginUser.getPassword() == null || loginUser.getUsername().isEmpty())
			throw new ServletException("Password polje ne smije biti prazno.");
		
		try {
			String userEmail = registeredUserDao.findUser(loginUser.getUsername(), loginUser.getPassword());
			return userEmail;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new ServletException("RegiseredUserService Login exception");
		}
	}
	
	public String createRegisteredUser()	{
		
		return ""; 
	}

	
}
