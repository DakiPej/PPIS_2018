package com.example.ppis.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.RegisteredUser;
import com.example.ppis.models.Request;
import com.example.ppis.models.RequestMessage;
import com.example.ppis.repositories.RequestMessageRepository;

@Repository
public class RequestMessageDAO extends BaseDAO<RequestMessage, RequestMessageRepository>{
	
	public boolean existsByRequest(Request request)	{
		return this.repo.existsByRequest(request) ; 
	}
	public List<RequestMessage> getRequestMessagesByReceiver(RegisteredUser receiver)	{
		
		List<RequestMessage> requestMessages ; 
		
		try {
			requestMessages = this.repo.findAllByReceiverOrderByDateDesc(receiver); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return requestMessages ; 
	}
	
	public List<RequestMessage> getRequestMessagesBySender(RegisteredUser sender)	{
		
		List<RequestMessage> requestMessages ; 
		
		try {
			requestMessages = this.repo.findAllBySenderOrderByDateDesc(sender); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return requestMessages ; 
	}
	
	public List<RequestMessage> getRequestMessagesByRequest(Request request)	{
		
		List<RequestMessage> requestMessages ; 
		
		try {
			requestMessages = this.repo.findAllByRequestOrderByDateDesc(request); 
		} catch (Exception e) {
			throw e ; 
		}
		
		return requestMessages ; 
	}
}
