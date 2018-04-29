package com.example.ppis.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

public abstract class BaseDAO <M, R extends CrudRepository<M, Long>>{
	
	protected R repo; 
	
	@Autowired
	public void setRepo(R repository)	{
		repo = repository;
	}
	
	public M create (M model)	{
		return repo.save(model); 
	}
	public void delete (long id)	{
		repo.deleteById(id);
	}
	public M one (long id)	{
		return repo.findById(id).get();
	}
	
	public boolean existsById(long id)	{
		return repo.existsById(id) ; 
	}
}
