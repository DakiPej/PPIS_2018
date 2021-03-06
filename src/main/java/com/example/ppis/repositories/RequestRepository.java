package com.example.ppis.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.ContactMethod;
import com.example.ppis.models.Department;
import com.example.ppis.models.Incident;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.models.Request;

public interface RequestRepository extends CrudRepository<Request, Long>{
	
	public List<Request> findAllByRegisteredUser(RegisteredUser registeredUser);
	public List<Request> findAllByAdmin(RegisteredUser admin) ; 
	public List<Request> findAllByResolverUserAndAdmin(RegisteredUser resolverUser, RegisteredUser admin) ;
	public List<Request> findAllByRegisteredUserAndResolverUserIsNull(RegisteredUser registeredUser) ;
	public List<Request> findAllByRegisteredUserAndResolverUserNotNull(RegisteredUser registeredUser) ; 
	public List<Request> findAllByResolverUser(RegisteredUser resolverUser); 
	public List<Request> findAllByResolverUserAndDepartment(RegisteredUser resolverUser, Department department) ; 
	
	public List<Request> findAllByResolverUserAndPriorityGreaterThan(RegisteredUser resolverUser, int priority) ; 
	public List<Request> findAllByResolverUserAndPriorityLessThan(RegisteredUser resolverUser, int priority) ; 
	
	public List<Request> findAllByRegisteredUserAndUrgencyGreaterThan(RegisteredUser registeredUser, int urgency) ; 
	public List<Request> findAllByRegisteredUserAndUrgencyLessThan(RegisteredUser registeredUser, int urgency) ; 
	public List<Request> findAllByResolverUserAndUrgencyGreaterThan(RegisteredUser resolverUser, int urgency) ; 
	public List<Request> findAllByResolverUserAndUrgencyLessThan(RegisteredUser resolverUser, int urgency) ; 
	
	//public List<Request> findAllByRegisteredUserAndResolved(RegisteredUser registeredUSer, Boolean resolved) ;
	//public List<Request> findAllByResolverUserAndResolved(RegisteredUser resolverUser, Boolean resolved) ; 
	
	public List<Request> findAllByRegisteredUserAndClosed(RegisteredUser registeredUser, Boolean closed) ;
	public List<Request> findAllByResolverUserAndClosed(RegisteredUser resolveruser, Boolean closed) ; 
	
	public List<Request> findAllByResolverUserAndContactMethod(RegisteredUser resolverUser, ContactMethod contactMethod);
	
	public List<Request> findAllByDepartment(Department department);
	public Request findRequestById(Long id);
	
	public List<Request> findAllByResolverUserAndTitle(RegisteredUser resolverUser, String title); 
	
	public int countByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(Date start, Date end) ;
	public int countByClosedDateGreaterThanEqualAndClosedDateLessThanEqual(Date start, Date end) ; 
	public int countByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqualAndResolverUserNotNull(Date start, Date end) ; 
	public int countByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqualAndResolverUserIsNull(Date start, Date end) ; 
	public int countByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqualAndEscalated(Date start, Date end, boolean escalated) ; 
}
