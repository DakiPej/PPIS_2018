package com.example.ppis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.ContactMethod;
import com.example.ppis.models.Department;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.models.Request;
import com.example.ppis.repositories.RequestRepository;

@Repository
public class RequestDAO extends BaseDAO<Request, RequestRepository>{
	
	public List<Request> getRequestsByRegisteredUser(RegisteredUser registeredUser)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByRegisteredUser(registeredUser);
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}
	
	public List<Request> getRequestsByResolver(RegisteredUser resolverUser)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByResolverUser(resolverUser); 
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}
	
	public List<Request> getRequestsByGreaterPriority(int priority)	{
		
		List<Request> requests ;
		
		try {
			requests = this.repo.findAllByPriorityGreaterThan(priority); 
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}
	
	public List<Request> getRequestsByLowerPriority(int priority)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByPriorityLessThan(priority); 
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}

	public List<Request> getRequestsByGreaterUrgency(int urgency)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByUrgencyGreaterThan(urgency); 
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}
	
	public List<Request> getRequestsByLowerUrgency(int urgency)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByUrgencyLessThan(urgency); 
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}

	public List<Request> getRequestsByResolved(boolean resolved)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByResolved(resolved); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return requests ; 
	}
	
	public List<Request> getRequestsByClosed(boolean closed)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByClosed(closed); 
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}
	
	public List<Request> getRequestsByContactMethod(ContactMethod contactMethod)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByContactMethod(contactMethod); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return requests ; 
	}
	
	public List<Request> getRequestsByDepartment(Department department)	{
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByDepartment(department); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return requests ; 
	}
	
	public List<Request> getRequestsByTitle(String title)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByTitle(title); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return requests ; 
	}
}
