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
}
