package com.example.ppis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.ppis.services.RequestLogService;
import com.example.ppis.services.RequestService;
import com.example.ppis.dao.RegisteredUserDAO;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.models.Request;
import com.example.ppis.models.RequestLog;

@CrossOrigin
@RestController
@RequestMapping("/requests")
public class RequestController {
	RequestService requestService ; 
	RegisteredUserDAO registeredUserDAO ; 
	RequestLogService requestLogService ; 
	@Autowired
	public void setServices(RequestService requestService, RequestLogService requestLogService)	{
		this.requestService = requestService ; 
		this.requestLogService = requestLogService ; 
	}
	@Autowired
	public void setRegisteredUserDAO(RegisteredUserDAO registeredUserDao)	{
		this.registeredUserDAO = registeredUserDAO ; 
	}
	
	
	private static class NewRequest	{
		public String registeredUserUsername ; 
		public String contactMethod ; 
		public String title ; 
		public String description ; 
		public Integer urgency ; 
	}
	private static class RequestStatus	{
		public long requestId ; 
		public String username ; 
		public boolean workingOn ; 
	}
	private static class AssignRequestInfo {
		public long requestId ; 
		public String departmentName ; 
		public Integer priority ; 
		
	}
	private static class RequestInformation	{
		public long requestId ; 
		public String resolverUsername ; 
	}
	private static class UserInformation	{
		public String username; 
	}
	private static class FilterInformation	{
		public String username ; 
		public int urgency_or_priority ; 
		public boolean greater ; 
	}
	private static class ClosedInformation	{
		public String username ; 
		public boolean closed ; 
	}
	
	
	@RequestMapping(value="", method=RequestMethod.POST)	
	public ResponseEntity createNewRequest(@RequestBody final NewRequest newRequest)	{
		
		try {
			System.out.println("Username : " + newRequest.registeredUserUsername
					+ "\n ContactMethod : " + newRequest.contactMethod
					+ "\n Title : " + newRequest.title
					+ "\n Description : " + newRequest.description
					+ "\b Urgency : " + newRequest.urgency);
			String response = this.requestService.saveNewRequest(newRequest.registeredUserUsername
					, newRequest.contactMethod
					, newRequest.title
					, newRequest.description
					, newRequest.urgency) ;
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	@RequestMapping(value="/getAll", method=RequestMethod.POST)
	public ResponseEntity getRequests(@RequestBody final UserInformation info)	{
		try {
			
			List<Request> requests; 
			if(this.registeredUserDAO.findUserByUsername(info.username).getUserType().equals("Korisnik"))	
				requests = this.requestService.getRequestsByRegisteredUser(info.username) ; 
			else requests = this.requestService.getRequestsByResolver(info.username) ; 
			
			return ResponseEntity.status(HttpStatus.OK).body(requests) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	
	@RequestMapping(value="/requestStatus", method=RequestMethod.GET)
	public ResponseEntity getRequestStatuses(@RequestBody final RequestStatus reqStatus)	{
		try {
			List<Request> requests ; 
			if(!reqStatus.workingOn)
				requests = this.requestService.getRequestByRegisteredUserAndResolverUser(reqStatus.username, null) ;
			else requests = this.requestService.getRequestByRegisteredUserAndResolverUser(reqStatus.username, "") ; 
			
			return ResponseEntity.status(HttpStatus.OK).body(requests) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}

	@RequestMapping(value="unassignedRequests_ByDepartments", method=RequestMethod.POST)
	public ResponseEntity assignRequestToDepartment(@RequestBody final AssignRequestInfo assignRequestInfo)	{
		try {
			String response = this.requestService.assignRequestToDepartment(assignRequestInfo.requestId
					, assignRequestInfo.departmentName
					, assignRequestInfo.priority) ;
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	@RequestMapping(value="/unassignedRequests_ByDepartments", method=RequestMethod.GET)
	public ResponseEntity getAllUnassignedRequestsByDepartments(/*@RequestBody final UserInformation userInfo*/)	{
		try { 
			List<Request> requests ; 	
			requests = this.requestService.getRequestsByDepartment(null) ;
			return ResponseEntity.status(HttpStatus.OK).body(requests); 
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()); 
		}
	}
	@RequestMapping(value="unassignedRequests_ByDepartments", method=RequestMethod.PUT)
	public ResponseEntity reasignRequestToDepartment(@RequestBody final AssignRequestInfo assignRequestInfo)	{
		try {
			String response = this.requestService.reassignRequest(assignRequestInfo.requestId
					, assignRequestInfo.departmentName
					, assignRequestInfo.priority) ;
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	
	
	@RequestMapping(value="/escalatedRequests", method=RequestMethod.PUT)
	public ResponseEntity escalateRequest(@RequestBody final RequestInformation info)	{
		try {
			String response = this.requestService.escalateRequest(info.requestId, info.resolverUsername) ;
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ;
		}
	}
	
	
	@RequestMapping(value="unassignedRequests_ToResolvers", method=RequestMethod.POST)
	public ResponseEntity assignRequestToResolver(@RequestBody final RequestInformation info)	{
		try {
			String response = this.requestService.assignRequestToResolver(info.requestId
					, info.resolverUsername) ;
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	@RequestMapping(value="unassignedRequests_ToResolvers", method=RequestMethod.GET)
	public ResponseEntity getAllUnassignedRequestsByResolvers()	{
		try {
			List<Request> requests = this.requestService.getRequestsByResolver(null) ; 
			return ResponseEntity.status(HttpStatus.OK).body(requests); 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}

	
	@RequestMapping(value="/requestsByPriority", method=RequestMethod.GET)
	public ResponseEntity getAllByPriority(@RequestBody final FilterInformation info)	{
		try {
			List<Request> requests ; 
			if(info.greater)
				requests = this.requestService.getRequestsByResolverUserAndGreaterPriority(info.username, info.urgency_or_priority) ; 
			else requests = this.requestService.getRequestsByResolverUserAndLowerPriority(info.username, info.urgency_or_priority) ;
			
			return ResponseEntity.status(HttpStatus.OK).body(requests) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	
	
	@RequestMapping(value="/requestsByUrgency", method=RequestMethod.GET)
	public ResponseEntity getAllByUrgency(@RequestBody final FilterInformation info)	{
		try {
			List<Request> requests ; 
			if(this.registeredUserDAO.findUserByUsername(info.username).getUserType().equals("Korisnik"))	{
				if(info.greater)
					requests = this.requestService.getRequestByRegisteredUserAndGreaterUrgency(info.username, info.urgency_or_priority); 
				else requests = this.requestService.getRequestByRegisteredUserAndLowerUrgency(info.username, info.urgency_or_priority) ; 
			}	else	{
				if(info.greater)
					requests = this.requestService.getRequestsByResolverUserAndGreaterUrgency(info.username, info.urgency_or_priority); 
				else requests = this.requestService.getRequestsByResolverUserAndLowerUrgency(info.username, info.urgency_or_priority) ;
			}
			
			return ResponseEntity.status(HttpStatus.OK).body(requests) ; 
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}

	
	@RequestMapping(value="/closedRequests", method=RequestMethod.GET)
	public ResponseEntity getAllByClosed(@RequestBody final ClosedInformation info)	{
		try {
			List<Request> requests ; 
			if(this.registeredUserDAO.findUserByUsername(info.username).getUserType().equals("Korisnik"))
				requests = this.requestService.getRequestsByRegisteredUserAndClosed(info.username, info.closed) ; 
			else requests = this.requestService.getRequestsByResolverUserAndClosed(info.username, info.closed) ; 
			
			return ResponseEntity.status(HttpStatus.OK).body(requests) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ;
		}
	}
	
	@RequestMapping(value="/RequestLogs", method=RequestMethod.GET)
	public ResponseEntity getRequestLogsByRequest(@RequestBody final RequestInformation info)	{
		try {
			List<RequestLog> logs ;
			if(!this.registeredUserDAO.existsByUsername(info.resolverUsername)
					|| !this.registeredUserDAO.findUserByUsername(info.resolverUsername)
						.getUserType()
						.getTypeName()
						.equals("Korisnik"))
				logs = this.requestLogService.getRequstLogsByRequest(info.requestId) ;
			
			else throw new IllegalArgumentException("The user does not have the permission to access the logs.") ;
				
			return ResponseEntity.status(HttpStatus.OK).body(logs) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
}
