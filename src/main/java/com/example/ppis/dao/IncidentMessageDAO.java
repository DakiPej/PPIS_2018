package com.example.ppis.dao;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.Incident;
import com.example.ppis.models.IncidentMessage;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.repositories.IncidentMessageRepository;

@Repository
public class IncidentMessageDAO extends BaseDAO<IncidentMessage, IncidentMessageRepository>{

	public List<IncidentMessage> getIncidentMessagesByReceiver(RegisteredUser receiver)	{
		
		List<IncidentMessage> incidentMessages ; 
		
		try {
			incidentMessages = this.repo.findAllByReceiverOrderByDateDesc(receiver); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return incidentMessages ; 
	}
	
	public List<IncidentMessage> getIncidentMessagesBySender(RegisteredUser sender)	{
		
		List<IncidentMessage> incidentMessages ; 
		
		try {
			incidentMessages = this.repo.findAllBySenderOrderByDateDesc(sender); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return incidentMessages ; 
	}
	
	public List<IncidentMessage> getIncidentMessagesByIncident(Incident incident)	{
		
		List<IncidentMessage> incidentMessages ; 
		
		try {
			incidentMessages = this.repo.findAllByIncidentOrderByDateDesc(incident); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return incidentMessages ; 
	}
	
}
