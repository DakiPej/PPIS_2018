package com.example.ppis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ppis.services.RequestLogService;
import com.example.ppis.services.RequestService;
import com.example.ppis.dao.RegisteredUserDAO;
import com.example.ppis.models.Request;
import com.example.ppis.models.RequestLog;
import com.example.ppis.ViewModels.Employee_RequestVM;
import com.example.ppis.ViewModels.EndUser_RequestVM ; 

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
		public Integer requestId ; 
		public String username ; 
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
	
	EndUser_RequestVM EU_vmConverter = new EndUser_RequestVM() ; 
	Employee_RequestVM e_vmConverter = new Employee_RequestVM() ; 
	
	@RequestMapping(value="", method=RequestMethod.POST)	
	public ResponseEntity createNewRequest(@RequestBody final NewRequest newRequest)	{
		
		try {
			/*System.out.println("Username : " + newRequest.registeredUserUsername
					+ "\n ContactMethod : " + newRequest.contactMethod
					+ "\n Title : " + newRequest.title
					+ "\n Description : " + newRequest.description
					+ "\b Urgency : " + newRequest.urgency);*/
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
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity getRequests(@RequestBody final RequestInformation info)	{
		try {
			if(info.requestId == null)	{
				List<Request> requests ;
				requests = this.requestService.getRequests(info.username) ;
				
				if(this.registeredUserDAO.findUserByUsername(info.username).getUserType().getTypeName().equals("Korisnik"))	{
					List<EndUser_RequestVM> vms = EU_vmConverter.convertToVM(requests); 
					return ResponseEntity.status(HttpStatus.OK).body(vms) ; 
				}	else	{
					List<Employee_RequestVM> vms = e_vmConverter.convertToVM(requests) ; 
					return ResponseEntity.status(HttpStatus.OK).body(vms) ;
				}
			}
			else	{
				Request request ; 
				request = this.requestService.getRequest(info.requestId, info.username) ; 
				return ResponseEntity.status(HttpStatus.OK).body(request) ; 
			}
			/*
			if(this.registeredUserDAO.findUserByUsername(info.username).getUserType().equals("Korisnik"))	
				requests = this.requestService.getRequestsByRegisteredUser(info.username) ; 
			else requests = this.requestService.getRequestsByResolver(info.username) ; 
			*/
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}

	@RequestMapping(value="/requestStatus", method=RequestMethod.GET)
	public ResponseEntity getRequestStatuses(@RequestBody final RequestStatus info)	{
		try {
			List<Request> requests ; 
			if(!info.workingOn)
				requests = this.requestService.getRequestByRegisteredUserAndResolverUser(info.username, null) ;
			else requests = this.requestService.getRequestByRegisteredUserAndResolverUser(info.username, "") ; 
			
			if(this.registeredUserDAO.findUserByUsername(info.username).getUserType().getTypeName().equals("Korisnik"))	{
				List<EndUser_RequestVM> vms = EU_vmConverter.convertToVM(requests); 
				return ResponseEntity.status(HttpStatus.OK).body(vms) ; 
			}	else	{
				List<Employee_RequestVM> vms = e_vmConverter.convertToVM(requests) ; 
				return ResponseEntity.status(HttpStatus.OK).body(vms) ;
			}
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
			
			List<Employee_RequestVM> vms = e_vmConverter.convertToVM(requests) ; 
			return ResponseEntity.status(HttpStatus.OK).body(vms) ;
			
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
			String response = this.requestService.escalateRequest(info.requestId, info.username) ;
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ;
		}
	}
	
	
	@RequestMapping(value="unassignedRequests_ToResolvers", method=RequestMethod.POST)
	public ResponseEntity assignRequestToResolver(@RequestBody final RequestInformation info)	{
		try {
			String response = this.requestService.assignRequestToResolver(info.requestId
					, info.username) ;
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	@RequestMapping(value="unassignedRequests_ToResolvers", method=RequestMethod.GET)
	public ResponseEntity getAllUnassignedRequestsByResolvers()	{
		try {
			List<Request> requests = this.requestService.getRequestsByResolver(null) ; 
			
			List<Employee_RequestVM> vms = e_vmConverter.convertToVM(requests) ; 
			return ResponseEntity.status(HttpStatus.OK).body(vms) ;
			
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
			
			if(this.registeredUserDAO.findUserByUsername(info.username).getUserType().getTypeName().equals("Korisnik"))	{
				List<EndUser_RequestVM> vms = EU_vmConverter.convertToVM(requests); 
				return ResponseEntity.status(HttpStatus.OK).body(vms) ; 
			}	else	{
				List<Employee_RequestVM> vms = e_vmConverter.convertToVM(requests) ; 
				return ResponseEntity.status(HttpStatus.OK).body(vms) ;
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	
	
	@RequestMapping(value="/requestsByUrgency", method=RequestMethod.GET)
	public ResponseEntity getAllByUrgency(@RequestBody final FilterInformation info)	{
		try {
			List<Request> requests ; 
			/*if(this.registeredUserDAO.findUserByUsername(info.username).getUserType().equals("Korisnik"))	{
				if(info.greater)
					requests = this.requestService.getRequestByRegisteredUserAndGreaterUrgency(info.username, info.urgency_or_priority); 
				else requests = this.requestService.getRequestByRegisteredUserAndLowerUrgency(info.username, info.urgency_or_priority) ; 
			}	else	{
				if(info.greater)
					requests = this.requestService.getRequestsByResolverUserAndGreaterUrgency(info.username, info.urgency_or_priority); 
				else requests = this.requestService.getRequestsByResolverUserAndLowerUrgency(info.username, info.urgency_or_priority) ;
			}*/
			requests = this.requestService.getRequestsByUrgency(info.username, info.urgency_or_priority, info.greater) ; 
			
			if(this.registeredUserDAO.findUserByUsername(info.username).getUserType().getTypeName().equals("Korisnik"))	{
				List<EndUser_RequestVM> vms = EU_vmConverter.convertToVM(requests); 
				return ResponseEntity.status(HttpStatus.OK).body(vms) ; 
			}	else	{
				List<Employee_RequestVM> vms = e_vmConverter.convertToVM(requests) ; 
				return ResponseEntity.status(HttpStatus.OK).body(vms) ;
			}
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}

	
	@RequestMapping(value="/closedRequests", method=RequestMethod.GET)
	public ResponseEntity getAllByClosed(@RequestBody final ClosedInformation info)	{
		try {
			List<Request> requests ; 
			/*if(this.registeredUserDAO.findUserByUsername(info.username).getUserType().equals("Korisnik"))
				requests = this.requestService.getRequestsByRegisteredUserAndClosed(info.username, info.closed) ; 
			else requests = this.requestService.getRequestsByResolverUserAndClosed(info.username, info.closed) ; 
			*/
			requests = this.requestService.getRequestsByClosed(info.username, info.closed) ;
			
			if(this.registeredUserDAO.findUserByUsername(info.username).getUserType().getTypeName().equals("Korisnik"))	{
				List<EndUser_RequestVM> vms = EU_vmConverter.convertToVM(requests); 
				return ResponseEntity.status(HttpStatus.OK).body(vms) ; 
			}	else	{
				List<Employee_RequestVM> vms = e_vmConverter.convertToVM(requests) ; 
				return ResponseEntity.status(HttpStatus.OK).body(vms) ;
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ;
		}
	}
	
	@RequestMapping(value="/RequestLogs", method=RequestMethod.GET)
	public ResponseEntity getRequestLogsByRequest(@RequestBody final RequestInformation info)	{
		try {
			List<RequestLog> logs ;
			logs = this.requestLogService.getRequstLogsByRequest(info.requestId, info.username) ;
				
			return ResponseEntity.status(HttpStatus.OK).body(logs) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
}
