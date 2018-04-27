package com.example.ppis.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.ContactMethod;
import com.example.ppis.models.Incident;
import com.example.ppis.models.RegisteredUser;

public interface IncidentRepository extends CrudRepository<Incident, Long>{
	
	public List<Incident> findAllByRegisteredUser(RegisteredUser registeredUser);
	public List<Incident> findAllByResolverUser(RegisteredUser resolverUser); 
	
	public List<Incident> findAllByPriorityGreaterThan(int priority); 
	public List<Incident> findAllByPriorityLessThan(int priority); 
	
	public List<Incident> findAllByUrgencyGreaterThan(int urgency); 
	public List<Incident> findAllByUrgencyLessThan(int urgency); 
	
	public List<Incident> findAllByResolved(boolean resolved);
	public List<Incident> findAllByClosed(boolean closed); 
	
	public List<Incident> findAllByContactMethod(ContactMethod contactMethod);
	
	public Incident findByTitle(String title); 
	
}
