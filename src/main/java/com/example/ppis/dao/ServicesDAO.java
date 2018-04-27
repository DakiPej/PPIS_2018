package com.example.ppis.dao;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.Services;
import com.example.ppis.repositories.ServicesRepository;

@Repository
public class ServicesDAO extends BaseDAO<Services, ServicesRepository>{

	public Long count() {
		return this.repo.count();
	}

	public Services getServiceByName(String serviceName) {
		return this.repo.findByServiceName(serviceName);
	}
}
