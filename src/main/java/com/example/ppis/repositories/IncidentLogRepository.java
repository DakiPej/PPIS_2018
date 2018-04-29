package com.example.ppis.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.Incident;
import com.example.ppis.models.IncidentLog;

public interface IncidentLogRepository extends CrudRepository<IncidentLog, Long>{
	
	public List<IncidentLog> getAllByIncident (Incident incident);
	public boolean existsByIncident (Incident incident) ; 
}
