package com.example.ppis.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ppis.dao.RegisteredUserDAO;
import com.example.ppis.dao.RequestDAO;
import com.example.ppis.dao.RequestLogDAO;
import com.example.ppis.models.Request;
import com.example.ppis.models.RequestLog;

@Service
public class RequestLogService {
	
	RequestLogDAO requestLogDao ; 
	RequestDAO requestDao ;
	RegisteredUserDAO registeredUserDao ; 
	
	@Autowired
	public void setDAOs(RequestLogDAO requestLogDao
			, RequestDAO requestDao
			, RegisteredUserDAO registeredUserDao)	{
		this.requestLogDao = requestLogDao ;
		this.requestDao = requestDao ; 
		this.registeredUserDao = registeredUserDao ; 
	}
	
	public List<RequestLog> getRequstLogsByRequest(long requestId, String username)	{
		List<RequestLog> logs ; 
		
		try {
			if(requestId < 0 || !this.requestDao.existsById(requestId)
					|| username == null || username.length() == 0 || this.registeredUserDao.existsByUsername(username))
				throw new IllegalArgumentException("The request id is not specified or the request does not exist or the user is unspecified or does not exist.") ; 
			if(this.registeredUserDao.findUserByUsername(username).getUserType().equals("Korisnik"))
				throw new IllegalArgumentException("The user does not have access to the logs.") ;
			
			logs = this.requestLogDao.getRequestLogsByRequest(
					this.requestDao.one(requestId)) ;
			
		} catch (Exception e) {
			throw e ; 
		}
		
		return logs ; 
	}
}
