package com.example.ppis.dao;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.Incident;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.repositories.IncidentRepository;

@Repository
public class IncidentDAO extends BaseDAO<Incident, IncidentRepository>{
	
	public Incident getIncidentByID(Long id) {
        return this.repo.findByID(id);
    }
}
