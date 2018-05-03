package com.example.ppis.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class RequestLog {
	
	@Id
	@GeneratedValue
	long id ; 
	
	@ManyToOne
	Request request ; 
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	Date eventDate ; 
	
	@NotNull
	String logDescription ;
	
	public RequestLog()	{
		
	}

	public RequestLog(Request request, Date eventDate, String logDescription)	{
		
		this.request = request ; 
		this.eventDate = eventDate ; 
		this.logDescription = logDescription ;
		
	}
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public String getLogDescription() {
		return logDescription;
	}

	public void setLogDescription(String logDescription) {
		this.logDescription = logDescription;
	} 
}
