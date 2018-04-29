package com.example.ppis.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.ContactMethod;
import com.example.ppis.models.Department;
import com.example.ppis.models.Incident;
import com.example.ppis.models.RegisteredUser;

public interface IncidentRepository extends CrudRepository<Incident, Long>{
	
	
	public List<Incident> findAllByRegisteredUser(RegisteredUser registeredUser);
	public List<Incident> findAllByResolverUser(RegisteredUser resolverUser); 
	
	public List<Incident> findAllByResolverUserAndPriorityGreaterThan(RegisteredUser resolverUser, int priority); 
	public List<Incident> findAllByResolverUserAndPriorityLessThan(RegisteredUser resolverUser, int priority); 
	
	public List<Incident> findAllByRegisteredUserAndUrgencyGreaterThan(RegisteredUser registeredUser, int urgency); 
	public List<Incident> findAllByRegisteredUserAndUrgencyLessThan(RegisteredUser registeredUser, int urgency); 
	public List<Incident> findAllByResolverUserAndUrgencyGreaterThan(RegisteredUser resolverUser, int urgency) ; 
	public List<Incident> findAllByResolverUserAndUrgencyLessThan(RegisteredUser resolverUser, int urgency) ;
	
	
	public List<Incident> findAllByResolved(boolean resolved);
	public List<Incident> findAllByClosed(boolean closed); 
	
	public List<Incident> findAllByContactMethod(ContactMethod contactMethod);
	
	public List<Incident> findAllByDepartment(Department department);
	
	public List<Incident> findAllByTitle(String title); 
	
}
