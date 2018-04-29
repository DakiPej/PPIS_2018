package com.example.ppis.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.RegisteredUser;
import com.example.ppis.models.Request;
import com.example.ppis.models.RequestMessage;

public interface RequestMessageRepository extends CrudRepository<RequestMessage, Long>{
	
	public boolean existsByRequest(Request request) ; 
	
	public List<RequestMessage> findAllByReceiverOrderByDateDesc(RegisteredUser receiver) ; 
	public List<RequestMessage> findAllBySenderOrderByDateDesc(RegisteredUser sender)	; 
	
	public List<RequestMessage> findAllByRequestOrderByDateDesc(Request request); 
}
