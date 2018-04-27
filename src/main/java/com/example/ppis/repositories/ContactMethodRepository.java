package com.example.ppis.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.ContactMethod;

public interface ContactMethodRepository extends CrudRepository<ContactMethod, Long>{
	
	public ContactMethod getByContactMethodName	(String contactMethodName); 
}
