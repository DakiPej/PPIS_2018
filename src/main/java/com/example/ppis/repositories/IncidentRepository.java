package com.example.ppis.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.Incident;

public interface IncidentRepository extends CrudRepository<Incident, Long>{

}