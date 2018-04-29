package com.example.ppis.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class RequestMessage {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@ManyToOne
	private RegisteredUser sender;

	@NotNull
	@ManyToOne
	private RegisteredUser receiver;

	@NotNull
	@ManyToOne
	private Request request;
	
	@NotNull
	@Size(max = 2000)
	private String message;
	
	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	public RequestMessage ()	{
		
	}
	
	public RequestMessage (RegisteredUser sender
			, RegisteredUser receiver
			, Request request
			, String message
			)	{
		
		this.sender = sender ;
		this.receiver = receiver ; 
		this.request = request ; 
		this.message = message ; 
		date = new Date() ; 
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RegisteredUser getSender() {
		return sender;
	}

	public void setSender(RegisteredUser sender) {
		this.sender = sender;
	}

	public RegisteredUser getReceiver() {
		return receiver;
	}

	public void setReceiver(RegisteredUser receiver) {
		this.receiver = receiver;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
