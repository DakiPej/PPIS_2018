package com.example.ppis.dao;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.ContactMethod;
import com.example.ppis.repositories.ContactMethodRepository;

@Repository
public class ContactMethodDAO extends BaseDAO<ContactMethod, ContactMethodRepository>{
	
	public ContactMethod getContactMethodByName	(String contactMethodName)	{
		ContactMethod contactMethod; 
		
		try {
			contactMethod = this.repo.getByContactMethodName(contactMethodName);
		} catch (Exception e) {
			throw e; 
		}
		return contactMethod; 
	}
}
