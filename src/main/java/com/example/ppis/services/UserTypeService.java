package com.example.ppis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppis.dao.UserTypeDAO;

@Service
public class UserTypeService {
	
	UserTypeDAO userTypeDao; 
	
	@Autowired
	public void setUserTypeDao(UserTypeDAO userTypeDao)	{
		this.userTypeDao = userTypeDao; 
	}
}
