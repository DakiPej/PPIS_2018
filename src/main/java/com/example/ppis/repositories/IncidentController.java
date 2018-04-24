package com.example.ppis.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.Incident;

public interface IncidentController extends CrudRepository<Incident, Long>{

}
