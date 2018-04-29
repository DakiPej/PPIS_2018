package com.example.ppis.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.UserType;

public interface UserTypeRepository extends CrudRepository<UserType, Long>{
	
	public boolean existsByTamePane(String typeName) ; 
	public UserType findDistinctUserTypeByTypeName(String typeName);
	
}
