package com.example.ppis.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.Request;
import com.example.ppis.models.RequestLog;

public interface RequestLogRepository extends CrudRepository<RequestLog, Long>{
	
	public List<RequestLog> findAllByRequest(Request request) ; 
}
