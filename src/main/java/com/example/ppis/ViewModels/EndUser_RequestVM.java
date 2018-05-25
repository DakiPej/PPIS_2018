package com.example.ppis.ViewModels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.ppis.models.Request;

public class EndUser_RequestVM {
	private long requestId ;
	private String creatorUser ;   
	private String title ; 
	private String description ; 
	private int urgency ; 
	private String contactMethod ; 
	private String status ; 
	private String creationDate ; 
	private String closedDate ; 
	
	public EndUser_RequestVM ()	{
		
	}
	
	public EndUser_RequestVM(long requestId
			, String creatorUser
			, String title
			, String description
			, int urgency
			, String contactMethod
			, String status 
			, String creationDate
			, String closedDate)	{
		
		this.requestId = requestId ; 
		this.creatorUser = creatorUser ; 
		this.title = title ; 
		this.description = description ; 
		this.urgency = urgency ; 
		this.contactMethod = contactMethod ; 
		this.status = status ; 
		this.creationDate = creationDate ; 
		this.closedDate = closedDate ; 
		
	}
	
	public List<EndUser_RequestVM> convertToVM(List<Request> requests)	{
		List<EndUser_RequestVM> requestVMs = new ArrayList<EndUser_RequestVM> ();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss") ;
		
		for(int i = 0 ; i < requests.size() ; i++)	{
			String status; 
			String closedDate ; 
			
			if(requests.get(i).getResolverUser() == null)
				status = "Not assigned." ; 
			else status = "Working on" ; 
			
			if(requests.get(i).getClosed())
				status = "Closed" ;
			
			if(requests.get(i).getClosedDate() != null)
				closedDate = df.format(requests.get(i).getClosedDate()) ; 
			else closedDate = "Not closed" ; 
			EndUser_RequestVM element = new EndUser_RequestVM(
					requests.get(i).getId()
					, requests.get(i).getRegisteredUser().getUsername()
					, requests.get(i).getTitle()
					, requests.get(i).getDescription()
					, requests.get(i).getUrgency()
					, requests.get(i).getContactMethod().getContactMethodName()
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
