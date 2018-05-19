package com.example.ppis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.ContactMethod;
import com.example.ppis.models.Department;
import com.example.ppis.models.Incident;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.repositories.IncidentRepository;

@Repository
public class IncidentDAO extends BaseDAO<Incident, IncidentRepository>{
	
	public List<Incident> getIncidentsByRegisteredUser(RegisteredUser registeredUser)	{
	
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByRegisteredUser(registeredUser);
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents; 
	}
	public List<Incident> getIncidentsByAdmin(RegisteredUser admin)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByAdmin(admin) ; 
		} catch (Exception e) {
			throw e ; 
		}
		
		return incidents ; 
	}
	public List<Incident> getIncidentsByResolverUserAndAdmin(RegisteredUser resolverUser, RegisteredUser admin)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByResolverUserAndAdmin(resolverUser, admin) ; 
		} catch (Exception e) {
			throw e ; 
		}
		
		return incidents ; 
	}
	public List<Incident> getIncidentsByRegisteredUserAndResolverUser(RegisteredUser registeredUser, RegisteredUser resolverUser)	{
		
		List<Incident> incidents; 
		
		try {
			incidents = this.repo.findAllByRegisteredUserAndResolverUser(registeredUser, resolverUser) ; 
		} catch (Exception e) {
			throw e ; 
		}
		
		return incidents ; 		
	}
	
	public List<Incident> getIncidentsByResolver(RegisteredUser resolverUser)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByResolverUser(resolverUser); 
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents; 
	}
	
	public List<Incident> getIncidentsByResolverUserAndGreaterPriority(RegisteredUser resolverUser, int priority)	{
		
		List<Incident> incidents ;
		
		try {
			incidents = this.repo.findAllByResolverUserAndPriorityGreaterThan(resolverUser, priority) ;
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents; 
	}
	public List<Incident> getIncidentsByResolverUserAndLowerPriority(RegisteredUser resolverUser, int priority)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByResolverUserAndPriorityLessThan(resolverUser, priority); 
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents ; 
	}

	public List<Incident> getIncidentsByRegisteredUserAndGreaterUrgency(RegisteredUser registeredUser, int urgency)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByRegisteredUserAndUrgencyGreaterThan(registeredUser, urgency) ; 
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents ; 
	}
	public List<Incident> getIncidentsByRegisteredUserAndLowerUrgency(RegisteredUser registeredUser, int urgency)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByRegisteredUserAndUrgencyLessThan(registeredUser, urgency); 
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents ; 
	}
	public List<Incident> getIncidentsByResolverUserAndGreaterUrgency(RegisteredUser resolverUser, int urgency)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByResolverUserAndUrgencyGreaterThan(resolverUser, urgency) ; 
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents ; 
	}
	public List<Incident> getIncidentsByResolverUserAndLowerUrgency(RegisteredUser resolverUser, int urgency)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByResolverUserAndUrgencyLessThan(resolverUser, urgency); 
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents ; 
	}

	public List<Incident> getIncidentsByRegisteredUserAndResolved(RegisteredUser registeredUser, Boolean resolved)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByRegisteredUserAndResolved(registeredUser, resolved); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return incidents ; 
	}
	public List<Incident> getIncidentsByResolverUserAndResolved(RegisteredUser resolverUser, Boolean resolved)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByRegisteredUserAndResolved(resolverUser, resolved); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return incidents ; 

	}
	
	public List<Incident> getIncidentsByRegisteredUserAndClosed(RegisteredUser registeredUser, Boolean closed)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByRegisteredUserAndClosed(registeredUser, closed); 
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents ; 
	}
	public List<Incident> getIncidentByResolveUserAndClosed(RegisteredUser resolverUser, Boolean closed)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByResolverUserAndClosed(resolverUser, closed); 
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents ; 
		
	}
	
	public List<Incident> getIncidentsByResolverUserAndContactMethod(RegisteredUser resolverUser, ContactMethod contactMethod)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByResolverUserAndContactMethod(resolverUser, contactMethod); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return incidents ; 
	}
	
	public List<Incident> getIncidentsByDepartment(Department department)	{
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByDepartment(department); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return incidents ; 
	}
	
	public List<Incident> getIncidentsByResolverUserAndTitle(RegisteredUser resolverUser, String title)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByResolverUserAndTitle(resolverUser, title); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return incidents ; 
	}

	public Incident getIncidentById(Long id) {
		Incident incident; 
		
		try {
			incident = this.repo.findIncidentById(id); 
		} catch (Exception e) {
			throw e; 
		}
		
		return incident;
	}

	public List<Incident> getAllByDepartmentNameAndResolverUserIsNull(Department department){
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByDepartmentAndResolverUserIsNull(department); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return incidents ;
	}
}
