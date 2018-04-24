package com.example.ppis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.example.ppis.services.IncidentService;

@RestController
public class IncidentController {
	@Autowired
	IncidentService incidentService;
	
	
}
