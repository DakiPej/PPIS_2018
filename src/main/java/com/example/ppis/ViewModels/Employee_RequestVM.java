package com.example.ppis.ViewModels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.ppis.models.Request;

public class Employee_RequestVM {
	private long requestId ;
	private String creatorUser ;
	private String resolver ; 
	private String admin ; 
	private String title ; 
	private String description ; 
	private int urgency ; 
	private String contactMethod ; 
	private String contactInfo ; 
	private String status ; 
	private String creationDate ; 
	private String closedDate ; 
	
	public Employee_RequestVM ()	{
		
	}
	
	public Employee_RequestVM (long requestId
			, String creatorUser
			, String resolver
			, String admin
			, String title
			, String description
			, int urgency
			, String contactMethod
			, String contactInfo 
			, String status 
			, String creationDate
			, String closedDate)	{
		this.requestId = requestId ; 
		this.creatorUser = creatorUser ; 
		this.resolver = resolver ; 
		this.admin = admin ; 
		this .urgency = urgency ; 
		this.contactMethod = contactMethod ; 
		this.contactInfo = contactInfo ; 
		this.status = status ; 
		this.creationDate = creationDate ; 
		this.closedDate = closedDate ; 
		
	}
	
	public List<Employee_RequestVM> convertToVM(List<Request> requests)	{
		
		List<Employee_RequestVM> requestVMs = new ArrayList<Employee_RequestVM> ();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss") ;
		
		for(int i = 0 ; i < requests.size() ; i++)	{
			String status; 
			String closedDate ; 
			String resolver ; 
			String admin ; 
			
			if(requests.get(i).getAdmin() == null)
				admin = "The request has not been picked by any administrator yet." ; 
			else admin = requests.get(i).getAdmin().getUsername() ; 
			
			if(requests.get(i).getResolverUser() == null)	{
				status = "Not assigned." ; 
				resolver = "The request has not been picked by any resolver yet." ; 
			}
			else {
				status = "Working on" ;
				resolver = requests.get(i).getResolverUser().getUsername() ; 
			}
			
			if(requests.get(i).getClosed())
				status = "Closed" ;
			
			if(requests.get(i).getClosedDate() != null)
				closedDate = df.format(requests.get(i).getClosedDate()) ; 
			else closedDate = "Not closed" ; 
			Employee_RequestVM element = new Employee_RequestVM(
					requests.get(i).getId()
					, requests.get(i).getRegisteredUser().getUsername()
					, resolver
					, admin 
					, requests.get(i).getTitle()
					, requests.get(i).getDescription()
					, requests.get(i).getUrgency()
					, requests.get(i).getContactMethod().getContactMethodName()
					, requests.get(i).getContactInfo() 
					, status
					, df.format(requests.get(i).getCreatedDate())
					, closedDate) ;
			
			requestVMs.add(element) ; 
		}
		
		return requestVMs ; 
		
	}

	public long getRequestId() {
		return requestId;
	}

	public void setRequestId(long requestId) {
		this.requestId = requestId;
	}

	public String getCreatorUser() {
		return creatorUser;
	}

	public void setCreatorUser(String creatorUser) {
		this.creatorUser = creatorUser;
	}

	public String getResolver() {
		return resolver;
	}

	public void setResolver(String resolver) {
		this.resolver = resolver;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getUrgency() {
		return urgency;
	}

	public void setUrgency(int urgency) {
		this.urgency = urgency;
	}

	public String getContactMethod() {
		return contactMethod;
	}

	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	public String getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}
}
