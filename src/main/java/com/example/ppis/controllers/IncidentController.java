package com.example.ppis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.example.ppis.controllers.forms.CreateIncidentForm;
import com.example.ppis.services.IncidentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
public class IncidentController {
	@Autowired
	IncidentService incidentService;
	
	@PostMapping(value="/createIncident")
	public ResponseEntity createIncident(@RequestBody @Valid CreateIncidentForm createIncidentForm) {
		try {
			if(incidentService.createIncident(createIncidentForm))
				return ResponseEntity.status(HttpStatus.OK).body(true);
			else 
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		} catch (Exception e) {
			//TODO: handle exception
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
}
