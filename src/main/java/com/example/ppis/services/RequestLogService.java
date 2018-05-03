package com.example.ppis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppis.dao.RequestDAO;
import com.example.ppis.dao.RequestLogDAO;
import com.example.ppis.models.Request;
import com.example.ppis.models.RequestLog;

@Service
public class RequestLogService {
	
	RequestLogDAO requestLogDao ; 
	RequestDAO requestDao ;
	
	@Autowired
	public void setDAOs(RequestLogDAO requestLogDao, 
			RequestDAO requestDao)	{
		this.requestLogDao = requestLogDao ;
		this.requestDao = requestDao ; 
	}
	
	public List<RequestLog> getRequstLogsByRequest(long requestId)	{
		List<RequestLog> logs ; 
		
		try {
			if(requestId < 0 || !this.requestDao.existsById(requestId))
				throw new IllegalArgumentException("The request id is not specified or the request does not exist.") ; 
			
			logs = this.requestLogDao.getRequestLogsByRequest(
					this.requestDao.one(requestId)) ;
			
		} catch (Exception e) {
			throw e ; 
		}
		
		return logs ; 
	}
}
