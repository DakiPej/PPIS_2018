package com.example.ppis.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
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
import com.example.ppis.ViewModels.Employee_RequestVM;
import com.example.ppis.ViewModels.EndUser_RequestVM ; 

@CrossOrigin
@RestController
@RequestMapping("/requests")
public class RequestController {
	RequestService requestService ; 
	RegisteredUserDAO registeredUserDAO ; 
	RequestLogService requestLogService ; 
	RegisteredUserDAO registeredUserDao ; 
	@Autowired
	public void setServices(RequestService requestService, RequestLogService requestLogService)	{
		this.requestService = requestService ; 
		this.requestLogService = requestLogService ; 
	}
	@Autowired
	public void setRegisteredUserDAO(RegisteredUserDAO registeredUserDao)	{
		this.registeredUserDao = registeredUserDao ; 
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
		//public Integer priority ; 
		
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
	
	@RequestMapping(value="/{username}/{requestId}", method=RequestMethod.GET)
	public ResponseEntity getOneRequest(@PathVariable("username") String username
			, @PathVariable("requestId") long requestId)	{
		try {

			Request request = this.requestService.getRequest(requestId, username) ; 
			Employee_RequestVM vm = this.e_vmConverter.convert(request) ; 
			return ResponseEntity.status(HttpStatus.OK).body(vm) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage()) ; 
		}
	}
	/*@RequestMapping(value="/{username}/{requestId}", method=RequestMethod.GET)
	public ResponseEntity getRequests(
			@PathVariable("username") String username
			, @PathVariable("requestId") Long requestId/*@RequestBody final RequestInformation info)	{
		try {
			if(requestId == -1)	{
				List<Request> requests ;
				requests = this.requestService.getRequests(username) ;
				System.out.println("evo dobavio sam ti requestove");
				if(requests.isEmpty())System.out.println("NEMA NISTA JEBO GA HLJEB");
				if(this.registeredUserDao.findUserByUsername(username).getUserType().getTypeName().equals("Korisnik"))	{
					List<EndUser_RequestVM> vms = EU_vmConverter.convertToVM(requests);
					System.out.println("saljem opet nesto");
					return ResponseEntity.status(HttpStatus.OK).body(vms) ; 
				}	else	{
					System.out.println("usao sam u else");
					List<Employee_RequestVM> vms = e_vmConverter.convertToVM(requests) ; 
					System.out.println("saljem nesto");
					return ResponseEntity.status(HttpStatus.OK).body(vms) ;
				}
			}
			else	{
				Request request ; 
				request = this.requestService.getRequest(requestId, username) ;
				System.out.println("e sacu ti ga poslat");
				return ResponseEntity.status(HttpStatus.OK).body(request) ; 
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
			*/
	@RequestMapping(value="/requestStatus/{username}/{requestId}/{workingOn}", method=RequestMethod.GET)
	public ResponseEntity getRequestStatuses(
			@PathVariable("username") String username
			, @PathVariable("requestId") Long requestId
			, @PathVariable("workingOn") boolean workingOn/*@RequestBody final RequestStatus info*/)	{
		try {
			List<Request> requests ; 
			if(!workingOn)
				requests = this.requestService.getRequestByRegisteredUserAndResolverUser(username, null) ;
			else requests = this.requestService.getRequestByRegisteredUserAndResolverUser(username, "") ; 
			
			if(this.registeredUserDAO.findUserByUsername(username).getUserType().getTypeName().equals("Korisnik"))	{
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

	@RequestMapping(value="/unassignedRequests_ByAdmins", method=RequestMethod.GET)
	public ResponseEntity getReqeustsByUnassignedAdmins()	{
		try {
			List<Request> requests ; 
			requests = this.requestService.getRequestsByAdmin(null) ; 
			return ResponseEntity.status(HttpStatus.OK).body(this.e_vmConverter.convertToVM(requests)) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	@RequestMapping(value="/unassignedRequests_ByAdmins", method=RequestMethod.PUT)
	public ResponseEntity assignRequestToAdmin(@RequestBody final RequestInformation info)	{
		try {
			
			System.out.println("Username je: " + info.username);
			System.out.println(("RequestID je : " + info.requestId));
			String response = this.requestService.assingRequestToAdmin(info.username, new Long(info.requestId));
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	@RequestMapping(value="/unassignedRequests_ByDepartments", method=RequestMethod.PUT)
	public ResponseEntity assignRequestToDepartment(@RequestBody final AssignRequestInfo assignRequestInfo)	{
		try {
			String response = this.requestService.assignRequestToDepartment(assignRequestInfo.requestId
					, assignRequestInfo.departmentName) ;
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	//PREPRAVITI DA DOBAVLJA SVE ONE ZA DEPARTMENT !!!!	
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
	@RequestMapping(value="unassignedRequests_ByDepartments", method=RequestMethod.POST)
	public ResponseEntity reassignRequestToDepartment(@RequestBody final AssignRequestInfo assignRequestInfo)	{
		try {
			String response = this.requestService.reassignRequest(assignRequestInfo.requestId
					, assignRequestInfo.departmentName) ;
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
	
	/*@RequestMapping(value="/cancelEscalation", method=RequestMethod.PUT)
	public ResponseEntity cancelEscalation(@RequestBody final RequestInformation info)	{
		
	}*/
	
	
	@RequestMapping(value="/unassignedRequests_ToResolvers", method=RequestMethod.PUT)
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

	
	@RequestMapping(value="/requestsByPriority/{username}/{priority}/{greater}", method=RequestMethod.GET)
	public ResponseEntity getAllByPriority(
			@PathVariable("username") String username
			, @PathVariable("priority") int priority
			, @PathVariable("greater") boolean greater /*@RequestBody final FilterInformation info*/)	{
		try {
			List<Request> requests ; 
			if(greater)
				requests = this.requestService.getRequestsByResolverUserAndGreaterPriority(username, priority) ; 
			else requests = this.requestService.getRequestsByResolverUserAndLowerPriority(username, priority) ;
			
			if(this.registeredUserDAO.findUserByUsername(username).getUserType().getTypeName().equals("Korisnik"))	{
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
	
	
	@RequestMapping(value="/requestsByUrgency/{username}/{urgency}/{greater}", method=RequestMethod.GET)
	public ResponseEntity getAllByUrgency(
			@PathVariable("username") String username
			, @PathVariable("urgency") int urgency
			, @PathVariable("greater") boolean greater/*@RequestBody final FilterInformation info*/)	{
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
			requests = this.requestService.getRequestsByUrgency(username, urgency, greater) ; 
			
			if(this.registeredUserDAO.findUserByUsername(username).getUserType().getTypeName().equals("Korisnik"))	{
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

	
	@RequestMapping(value="/closedRequests/{username}/{closed}", method=RequestMethod.GET)
	public ResponseEntity getAllByClosed(
			@PathVariable("username") String username
			, @PathVariable("closed") boolean closed/*@RequestBody final ClosedInformation info*/)	{
		try {
			List<Request> requests ; 
			/*if(this.registeredUserDAO.findUserByUsername(info.username).getUserType().equals("Korisnik"))
				requests = this.requestService.getRequestsByRegisteredUserAndClosed(info.username, info.closed) ; 
			else requests = this.requestService.getRequestsByResolverUserAndClosed(info.username, info.closed) ; 
			*/
			requests = this.requestService.getRequestsByClosed(username, closed) ;
			
			if(this.registeredUserDAO.findUserByUsername(username).getUserType().getTypeName().equals("Korisnik"))	{
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
	
	@RequestMapping(value="/RequestLogs/{username}/{requestId}", method=RequestMethod.GET)
	public ResponseEntity getRequestLogsByRequest(
			@PathVariable("username") String username
			, @PathVariable("requestId") Long requestId/*@RequestBody final RequestInformation info*/)	{
		try {
			List<RequestLog> logs ;
			logs = this.requestLogService.getRequstLogsByRequest(requestId, username) ;
				
			return ResponseEntity.status(HttpStatus.OK).body(logs) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	
	@RequestMapping(value="/unassigned/{username}", method=RequestMethod.GET)
	public ResponseEntity getAllUnass (@PathVariable("username") String username)	{
		try {
			List<Request> requests = new ArrayList<>() ; 
			requests = this.requestService.getAllUnassigned(username) ; 
			
			List<Employee_RequestVM> vms = e_vmConverter.convertToVM(requests) ;
			return ResponseEntity.status(HttpStatus.OK).body(vms) ; 
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	@RequestMapping(value="/{username}", method=RequestMethod.GET)
	public ResponseEntity getAll(@PathVariable("username") String username)	{
		try {
			List<Request> requests = new ArrayList<>() ;
			requests = this.requestService.getAll(username) ; 
			
			List<Employee_RequestVM> vms = e_vmConverter.convertToVM(requests) ; 
			return ResponseEntity.status(HttpStatus.OK).body(vms) ; 
			
			
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.OK).body(e.getMessage()) ; 
		}
	}
	
	private static class CloseRequestBody	{
		public long requestId ; 
		public String username ; 
	}
	
	@RequestMapping(value="/close", method=RequestMethod.POST)
	public ResponseEntity closeRequest(@RequestBody final CloseRequestBody info)	{
		try {
			String response = "" ; 
			response = this.requestService.closeRequest(info.requestId, info.username) ;
			
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}	
	}
	
	private static class ReportBody	{
		public String startDate ; 
		public String endDate ; 
	}
	@RequestMapping(value="/report", method=RequestMethod.POST)
	public ResponseEntity getReport(@RequestBody final ReportBody info)	{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(
					this.requestService.getReport(info.startDate, info.endDate)) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DOSLO JE DO GRESKE: " + e.getMessage()) ;
		}
	}
}
