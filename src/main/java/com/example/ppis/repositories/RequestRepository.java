package com.example.ppis.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.ContactMethod;
import com.example.ppis.models.Department;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.models.Request;

public interface RequestRepository extends CrudRepository<Request, Long>{
	
	public List<Request> findAllByRegisteredUser(RegisteredUser registeredUser);
	public List<Request> findAllByResolverUser(RegisteredUser resolverUser); 
	
	public List<Request> findAllByPriorityGreaterThan(int priority); 
	public List<Request> findAllByPriorityLessThan(int priority); 
	
	public List<Request> findAllByUrgencyGreaterThan(int urgency); 
	public List<Request> findAllByUrgencyLessThan(int urgency); 
	
	public List<Request> findAllByResolved(boolean resolved);
	public List<Request> findAllByClosed(boolean closed); 
	
	public List<Request> findAllByContactMethod(ContactMethod contactMethod);
	
	public List<Request> findAllByDepartment(Department department);
	
	public List<Request> findAllByTitle(String title); 
}
