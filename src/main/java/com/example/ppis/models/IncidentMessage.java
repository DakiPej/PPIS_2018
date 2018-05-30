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
public class IncidentMessage {
	
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
	private Incident incident;
	
	@NotNull
	@Size(max = 2000)
	private String message;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	public IncidentMessage()	{
		
	}
	
	public IncidentMessage(RegisteredUser sender
			, RegisteredUser receiver
			, Incident incident
			, String message, Date date)	{
		
		this.sender = sender ; 
		this.receiver = receiver ; 
		this.incident = incident ; 
		this.message = message ; 
		this.date = date;
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

	public void setReceiver(RegisteredUser reciever) {
		this.receiver = reciever;
	}

	public Incident getIncident() {
		return incident;
	}

	public void setIncident(Incident incident) {
		this.incident = incident;
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
