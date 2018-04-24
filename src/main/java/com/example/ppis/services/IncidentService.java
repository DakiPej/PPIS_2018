package com.example.ppis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppis.dao.IncidentDAO;

@Service
public class IncidentService {
	
	IncidentDAO incidentDao; 
	
	@Autowired
	public void setIncidentDAO(IncidentDAO incidentDao)	{
		this.incidentDao = incidentDao;
	}
}
