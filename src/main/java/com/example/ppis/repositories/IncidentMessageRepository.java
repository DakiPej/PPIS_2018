package com.example.ppis.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.Incident;
import com.example.ppis.models.IncidentMessage;
import com.example.ppis.models.RegisteredUser;

public interface IncidentMessageRepository extends CrudRepository<IncidentMessage, Long>{
	
	public boolean existsByIncident(Incident incident) ; 
	
	public List<IncidentMessage> findAllByReceiverOrderByDateDesc(RegisteredUser receiver) ; 
	public List<IncidentMessage> findAllBySenderOrderByDateDesc(RegisteredUser sender)	; 
	
	public List<IncidentMessage> findAllByIncidentOrderByDateDesc(Incident incident); 
	
}
