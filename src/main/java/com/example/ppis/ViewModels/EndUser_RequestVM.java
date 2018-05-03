package com.example.ppis.ViewModels;

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
			, Date creationDate
			, Date closedDate)	{
		
	}
	
	public List<EndUser_RequestVM> covnertToVM(List<Request> requests)	{
		List<EndUser_RequestVM> requestVMs = new ArrayList<EndUser_RequestVM> ();
		
		return requestVMs ; 
		
	}
}
