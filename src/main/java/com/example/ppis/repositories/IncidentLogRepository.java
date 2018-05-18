package com.example.ppis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.ppis.models.Incident;
import com.example.ppis.models.IncidentLog;

public interface IncidentLogRepository extends CrudRepository<IncidentLog, Long>{
	
	public List<IncidentLog> getAllByIncident (Incident incident);
	public boolean existsByIncident (Incident incident) ; 

	@Query("SELECT il FROM IncidentLog il WHERE il.incident.id = :id ORDER BY resolveDate DESC")
	public List<IncidentLog> findLastByIncidentOrderByResolveDate(@Param("id") Long id);
}
