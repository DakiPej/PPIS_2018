package com.example.ppis.dao;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.Incident;
import com.example.ppis.models.IncidentLog;
import com.example.ppis.repositories.IncidentLogRepository;

@Repository
public class IncidentLogDAO extends BaseDAO<IncidentLog, IncidentLogRepository>{

	public List<IncidentLog> getAllIncidentLogs	(Incident incident)	{
		List<IncidentLog> incidentLogs ; 
		try {
			incidentLogs = this.repo.getAllByIncident(incident); 
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw e; 
		}
		return incidentLogs; 
	}
	
	public boolean existsByIncident(Incident incident)	{
		return this.repo.existsByIncident(incident) ; 
	}
}
