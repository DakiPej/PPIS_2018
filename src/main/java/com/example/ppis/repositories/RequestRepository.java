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
	
	public List<Request> findAllByResolverUserAndPriorityGreaterThan(RegisteredUser resolverUser, int priority) ; 
	public List<Request> findAllByResolverUserAndPriorityLessThan(RegisteredUser resolverUser, int priority) ; 
	
	public List<Request> findAllByRegisteredUserAndUrgencyGreaterThan(RegisteredUser registeredUser, int urgency) ; 
	public List<Request> findAllByRegisteredUserAndUrgencyLessThan(RegisteredUser registeredUser, int urgency) ; 
	public List<Request> findAllByResolverUserAndUrgencyGreaterThan(RegisteredUser resolverUser, int urgency) ; 
	public List<Request> findAllByResolverUserAndUrgencyLessThan(RegisteredUser resolverUser, int urgency) ; 
	
	public List<Request> findAllByRegisteredUserAndResolved(RegisteredUser registeredUSer, boolean resolved) ;
	public List<Request> findAllByResolverUserAndResolved(RegisteredUser resolverUser, boolean resolved) ; 
	
	public List<Request> findAllByRegisteredUserAndClosed(RegisteredUser registeredUser, boolean closed) ;
	public List<Request> findAllByResolverUserAndClosed(RegisteredUser resolveruser, boolean closed) ; 
	
	public List<Request> findAllByResolverUserAndContactMethod(RegisteredUser resolverUser, ContactMethod contactMethod);
	
	public List<Request> findAllByDepartment(Department department);
	
	public List<Request> findAllByResolverUserAndTitle(RegisteredUser resolverUser, String title); 
}
