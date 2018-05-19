package com.example.ppis.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.ContactMethod;
import com.example.ppis.models.Department;
import com.example.ppis.models.Incident;
import com.example.ppis.models.RegisteredUser;

public interface IncidentRepository extends CrudRepository<Incident, Long>{
	
	
	public List<Incident> findAllByRegisteredUser(RegisteredUser registeredUser);
	public List<Incident> findAllByAdmin(RegisteredUser admin) ; 
	public List<Incident> findAllByResolverUserAndAdmin(RegisteredUser resolverUser, RegisteredUser admin) ;
	public List<Incident> findAllByRegisteredUserAndResolverUser(RegisteredUser registeredUser, RegisteredUser resolverUser) ; 
	public List<Incident> findAllByResolverUser(RegisteredUser resolverUser); 
	
	public List<Incident> findAllByResolverUserAndPriorityGreaterThan(RegisteredUser resolverUser, int priority) ; 
	public List<Incident> findAllByResolverUserAndPriorityLessThan(RegisteredUser resolverUser, int priority) ; 
	
	public List<Incident> findAllByRegisteredUserAndUrgencyGreaterThan(RegisteredUser registeredUser, int urgency) ; 
	public List<Incident> findAllByRegisteredUserAndUrgencyLessThan(RegisteredUser registeredUser, int urgency) ; 
	public List<Incident> findAllByResolverUserAndUrgencyGreaterThan(RegisteredUser resolverUser, int urgency) ; 
	public List<Incident> findAllByResolverUserAndUrgencyLessThan(RegisteredUser resolverUser, int urgency) ; 
	
	public List<Incident> findAllByRegisteredUserAndResolved(RegisteredUser registeredUSer, Boolean resolved) ;
	public List<Incident> findAllByResolverUserAndResolved(RegisteredUser resolverUser, Boolean resolved) ; 
	
	public List<Incident> findAllByRegisteredUserAndClosed(RegisteredUser registeredUser, Boolean closed) ;
	public List<Incident> findAllByResolverUserAndClosed(RegisteredUser resolveruser, Boolean closed) ; 
	
	public List<Incident> findAllByResolverUserAndContactMethod(RegisteredUser resolverUser, ContactMethod contactMethod);
	
	public List<Incident> findAllByDepartment(Department department);
	
	public List<Incident> findAllByResolverUserAndTitle(RegisteredUser resolverUser, String title); 

	public Incident findIncidentById(Long id);

	public List<Incident> findAllByDepartmentAndResolverUserIsNull(Department department);
	
}
