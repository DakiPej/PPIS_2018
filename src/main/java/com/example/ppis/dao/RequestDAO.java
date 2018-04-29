package com.example.ppis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.ContactMethod;
import com.example.ppis.models.Department;
import com.example.ppis.models.Incident;
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
	
	public List<Request> getRequestssByResolverUserAndGreaterPriority(RegisteredUser resolverUser, int priority)	{
		
		List<Request> requests ;
		
		try {
			requests = this.repo.findAllByResolverUserAndPriorityGreaterThan(resolverUser, priority) ;
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}
	public List<Request> getRequestsByResolverUserAndLowerPriority(RegisteredUser resolverUser, int priority)	{
		
		List<Request> requests; 
		
		try {
			requests = this.repo.findAllByResolverUserAndPriorityLessThan(resolverUser, priority); 
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}

	public List<Request> getRequestsByRegisteredUserAndGreaterUrgency(RegisteredUser registeredUser, int urgency)	{
		
		List<Request> requests; 
		
		try {
			requests = this.repo.findAllByRegisteredUserAndUrgencyGreaterThan(registeredUser, urgency) ; 
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}
	public List<Request> getRequestsByRegisteredUserAndLowerUrgency(RegisteredUser registeredUser, int urgency)	{
		
		List<Request> requests; 
		
		try {
			requests = this.repo.findAllByRegisteredUserAndUrgencyLessThan(registeredUser, urgency); 
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}
	public List<Request> getRequestsByResolverUserAndGreaterUrgency(RegisteredUser resolverUser, int urgency)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByResolverUserAndUrgencyGreaterThan(resolverUser, urgency) ; 
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}
	public List<Request> getRequestsByResolverUserAndLowerUrgency(RegisteredUser resolverUser, int urgency)	{
		
		List<Request> requests; 
		
		try {
			requests = this.repo.findAllByResolverUserAndUrgencyLessThan(resolverUser, urgency); 
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}

	public List<Request> getRequestsByRegisteredUserAndResolved(RegisteredUser registeredUser, boolean resolved)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByRegisteredUserAndResolved(registeredUser, resolved); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return requests ; 
	}
	public List<Request> getRequestsByResolverUserAndResolved(RegisteredUser resolverUser, boolean resolved)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByResolverUserAndResolved(resolverUser, resolved); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return requests ; 
	}
	
	public List<Request> getRequestsByRegisteredUserAndClosed(RegisteredUser registeredUser, boolean closed)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByRegisteredUserAndClosed(registeredUser, closed); 
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}
	public List<Request> getRequestsByResolverUserAndClosed(RegisteredUser resolverUser, boolean closed)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByResolverUserAndClosed(resolverUser, closed); 
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ;
	}
	
	public List<Request> getRequestsByResolverUserAndContactMethod(RegisteredUser resolverUser, ContactMethod contactMethod)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByResolverUserAndContactMethod(resolverUser, contactMethod); 
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
	
	public List<Request> getRequestsByResolverUserAndTitle(RegisteredUser resolverUser, String title)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByResolverUserAndTitle(resolverUser, title); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return requests ; 
	}
}
