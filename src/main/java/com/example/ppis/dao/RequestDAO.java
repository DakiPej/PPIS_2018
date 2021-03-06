package com.example.ppis.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ppis.ViewModels.RequestReport;
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
	public List<Request> getRequestsByAdmin(RegisteredUser admin)	{
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByAdmin(admin)	; 
		} catch (Exception e) {
			throw e ; 
		}
		
		return requests ; 
	}
	public List<Request> getRequestsByResolverUserAndAdmin(RegisteredUser resolverUser, RegisteredUser admin)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByResolverUserAndAdmin(resolverUser, admin) ; 
		} catch (Exception e) {
			throw e ; 
		}
		
		return requests ; 
	}
	public List<Request> getRequestsByRegisteredUserAndResolverUser(RegisteredUser registeredUser, RegisteredUser resolverUser)	{
		
		List<Request> requests ; 
		
		try {
			if(resolverUser == null)
				requests = this.repo.findAllByRegisteredUserAndResolverUserIsNull(registeredUser) ;
			else requests = this.repo.findAllByRegisteredUserAndResolverUserNotNull(registeredUser) ; 
		} catch (Exception e) {
			throw e ; 
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
	public List<Request> getRequestsByResolverAndDepartment(RegisteredUser resolverUser, Department department)	{
		try {
			
			return this.repo.findAllByResolverUserAndDepartment(resolverUser, department) ; 
		} catch (Exception e) {
			throw e ; 
		}
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

	/*public List<Request> getRequestsByRegisteredUserAndResolved(RegisteredUser registeredUser, Boolean resolved)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByRegisteredUserAndResolved(registeredUser, resolved); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return requests ; 
	}
	public List<Request> getRequestsByResolverUserAndResolved(RegisteredUser resolverUser, Boolean resolved)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByResolverUserAndResolved(resolverUser, resolved); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return requests ; 
	}*/
	
	public List<Request> getRequestsByRegisteredUserAndClosed(RegisteredUser registeredUser, Boolean closed)	{
		
		List<Request> requests ; 
		
		try {
			requests = this.repo.findAllByRegisteredUserAndClosed(registeredUser, closed); 
		} catch (Exception e) {
			throw e; 
		}
		
		return requests ; 
	}
	public List<Request> getRequestsByResolverUserAndClosed(RegisteredUser resolverUser, Boolean closed)	{
		
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
	public Request getRequestById(Long id) {
		Request request; 
		
		try {
			request = this.repo.findRequestById(id); 
		} catch (Exception e) {
			throw e; 
		}
		
		return request;
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
	
	public RequestReport getReport(Date start, Date end)	{
		try {
			int createdNumber ; 
			int closedNumber ; 
			int notWorkingOnNumber ; 
			int workingOnNumber ; 
			int escalatedNumber ; 
			int notEscalatedNumber ; 
			
			System.out.println(start);
			
			createdNumber = this.repo.countByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(start, end);
			closedNumber = this.repo.countByClosedDateGreaterThanEqualAndClosedDateLessThanEqual(start, end); 
			notWorkingOnNumber = this.repo.countByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqualAndResolverUserIsNull(start, end) ;
			workingOnNumber = this.repo.countByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqualAndResolverUserNotNull(start, end); 
			escalatedNumber = this.repo.countByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqualAndEscalated(start, end,  true) ;
			notEscalatedNumber = this.repo.countByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqualAndEscalated(start, end, false) ;
			
			return new RequestReport(createdNumber
									, closedNumber
									, notWorkingOnNumber
									, workingOnNumber
									, escalatedNumber
									, notEscalatedNumber) ;
			
		} catch (Exception e) {
			throw e ; 
		}
	}
}
