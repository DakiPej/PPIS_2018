package com.example.ppis.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.Services;

public interface ServicesRepository extends CrudRepository<Services, Long>{

	public boolean existsByServiceName(String serviceName) ; 
    public Services findByServiceName(String serviceName);
}
