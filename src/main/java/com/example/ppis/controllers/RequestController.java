package com.example.ppis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ppis.services.RequestService;

@RestController
@RequestMapping("/requests")
public class RequestController {
	RequestService requestService ; 
	
	@Autowired
	public void setRequestService(RequestService requestService)	{
		this.requestService = requestService ; 
	}
	
	private static class NewRequest	{
		public String registeredUserUsername ; 
		public String contactMethod ; 
		public String title ; 
		public String description ; 
		public Integer urgency ; 
	}
	@RequestMapping(value="", method=RequestMethod.POST)	
	public ResponseEntity createNewRequest(@RequestBody final NewRequest newRequest)	{
		
		try {
			String response = this.requestService.saveNewRequest(newRequest.registeredUserUsername
					, newRequest.contactMethod
					, newRequest.title
					, newRequest.description
					, newRequest.urgency) ;
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
			
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	
	private static class AssignRequestInfo {
		public long requestId ; 
		public String departmentName ; 
		public Integer priority ; 
		
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

	private static class ResolverAssignationInfo	{
		public long requestId ;
		public String resolverUsername ; 
	}
	@RequestMapping(value="unassignedRequests_ToResolvers", method=RequestMethod.POST)
	public ResponseEntity assignRequestToResolver(@RequestBody final ResolverAssignationInfo resolverAssignationInfo)	{
		try {
			String response = this.requestService.assignRequestToResolver(resolverAssignationInfo.requestId
					, resolverAssignationInfo.resolverUsername) ;
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	
	
}
