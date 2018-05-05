package com.example.ppis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.Request;
import com.example.ppis.models.RequestLog;
import com.example.ppis.repositories.RequestLogRepository;

@Repository
public class RequestLogDAO extends BaseDAO<RequestLog, RequestLogRepository>{
	
	public List<RequestLog> getRequestLogsByRequest(Request request)	{
		List<RequestLog> logs ; 
		
		try {
			logs = this.repo.findAllByRequest(request) ; 
		} catch (Exception e) {
			throw e ; 
		}
		
		return logs ; 
	}
}
