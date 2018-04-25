package com.example.ppis.events;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.example.ppis.dao.RegisteredUserDAO;
import com.example.ppis.dao.ServicesDAO;
import com.example.ppis.dao.UserTypeDAO;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.models.Services;
import com.example.ppis.models.UserType;

@Component
public class DatabaseSeeder {
	
	UserTypeDAO userTypeDAO;
	RegisteredUserDAO registeredUserDAO;
	ServicesDAO servicesDAO;
	
	@Autowired
	public void setServicesDAO(ServicesDAO servicesDAO) {
		this.servicesDAO = servicesDAO;
	}
	
	@Autowired
	public void setUserTypeDAO(UserTypeDAO userTypeDAO) {
		this.userTypeDAO = userTypeDAO;
	}
	
	@Autowired
	public void setRegisteredUserDAO(RegisteredUserDAO registeredUserDAO) {
		this.registeredUserDAO = registeredUserDAO;
	}
	
	@Autowired
	public DatabaseSeeder(UserTypeDAO userTypeDAO,
			RegisteredUserDAO registeredUserDAO) {
	}
	
	@EventListener
	public void seed(ContextRefreshedEvent event) {
		UserTypeTableSeed();
		RegisteredUserTableSeed();
		ServicesTableSeed();
	}
	
	private void UserTypeTableSeed() {
		if(userTypeDAO.count() == 0) {
			UserType ut = new UserType();
			ut.setTypeName("Korisnik");
			userTypeDAO.create(ut);
			
			ut = new UserType();
			ut.setTypeName("Administrator");
			userTypeDAO.create(ut);
		}
	}	
	
	private void RegisteredUserTableSeed() {
		
		System.out.println(userTypeDAO.findUserTypeByName("Korisnik"));
		
		if (registeredUserDAO.count() == 0) {
			RegisteredUser ru = new RegisteredUser();
			ru.setEmail("richard.h@email.com");
			ru.setFirstname("Richard");
			ru.setLastname("Hammond");
			ru.setPassword("password");
			ru.setUsername("hamster");
			ru.setUserType(userTypeDAO.findUserTypeByName("Korisnik"));
			registeredUserDAO.create(ru);
			
			ru = new RegisteredUser();
			ru.setEmail("jeremy.c@email.com");
			ru.setFirstname("Jeremy");
			ru.setLastname("Clarkson");
			ru.setPassword("password");
			ru.setUsername("clarkson");
			ru.setUserType(userTypeDAO.findUserTypeByName("Korisnik"));
			registeredUserDAO.create(ru);
			
			ru = new RegisteredUser();
			ru.setEmail("james.m@email.com");
			ru.setFirstname("James");
			ru.setLastname("May");
			ru.setPassword("password");
			ru.setUsername("captainSlow");
			ru.setUserType(userTypeDAO.findUserTypeByName("Korisnik"));
			registeredUserDAO.create(ru);
			
			ru = new RegisteredUser();
			ru.setEmail("admin.a@email.com");
			ru.setFirstname("Admin");
			ru.setLastname("Adminovic");
			ru.setPassword("password");
			ru.setUsername("admin");
			ru.setUserType(userTypeDAO.findUserTypeByName("Administrator"));
			registeredUserDAO.create(ru);
			
			ru = new RegisteredUser();
			ru.setEmail("andy.w@email.com");
			ru.setFirstname("Andy");
			ru.setLastname("Wilman");
			ru.setPassword("password");
			ru.setUsername("andy");
			ru.setUserType(userTypeDAO.findUserTypeByName("Administrator"));
			registeredUserDAO.create(ru);
		}
	}
	
	//also creates registered users services
	private void ServicesTableSeed() {
		if(servicesDAO.count() == 0) {
			
			/*
			 * 
			 * 
			 * */
			
			Services s = new Services();
			s.setServiceName("test");
			servicesDAO.create(s);
			
			s = new Services();
			s.setServiceName("proba");
			servicesDAO.create(s);

			RegisteredUser ru = registeredUserDAO.findByUsername("hamster");
			List<Services> ss = Arrays.asList(s);
			
			ru.setServices(ss);
			registeredUserDAO.create(ru);
		}
	}
}
