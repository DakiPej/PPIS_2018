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
	
	public List<Incident> getIncidentsByResolver(RegisteredUser resolverUser)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByResolverUser(resolverUser); 
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents; 
	}
	
	public List<Incident> getIncidentsByGreaterPriority(int priority)	{
		
		List<Incident> incidents ;
		
		try {
			incidents = this.repo.findAllByPriorityGreaterThan(priority); 
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents; 
	}
	
	public List<Incident> getIncidentsByLowerPriority(int priority)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByPriorityLessThan(priority); 
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents ; 
	}

	public List<Incident> getIncidentsByGreaterUrgency(int urgency)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByUrgencyGreaterThan(urgency); 
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents ; 
	}
	
	public List<Incident> getIncidentsByLowerUrgency(int urgency)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByUrgencyLessThan(urgency); 
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents ; 
	}

	public List<Incident> getIncidentsByResolved(boolean resolved)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByResolved(resolved); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return incidents ; 
	}
	
	public List<Incident> getIncidentsByClosed(boolean closed)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByClosed(closed); 
		} catch (Exception e) {
			throw e; 
		}
		
		return incidents ; 
	}
	
	public List<Incident> getIncidentsByContactMethod(ContactMethod contactMethod)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByContactMethod(contactMethod); 
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
	
	public List<Incident> getIncidentsByTitle(String title)	{
		
		List<Incident> incidents ; 
		
		try {
			incidents = this.repo.findAllByTitle(title); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return incidents ; 
	}
}
