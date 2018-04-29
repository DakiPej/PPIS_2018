package com.example.ppis.dao;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.UserType;
import com.example.ppis.repositories.UserTypeRepository;

@Repository
public class UserTypeDAO extends BaseDAO<UserType, UserTypeRepository>{
	
	public boolean existsByTypeName(String typeName)	{
		return this.repo.existsByTamePane(typeName) ;
	}
	public Long count() {
		return this.repo.count();
	}
	
	public UserType findUserTypeByName(String typeName) {
		return this.repo.findDistinctUserTypeByTypeName(typeName);
	}
}
