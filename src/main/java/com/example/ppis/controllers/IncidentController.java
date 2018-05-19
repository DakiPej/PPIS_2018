package com.example.ppis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import com.example.ppis.controllers.forms.AssignIncidentForm;
import com.example.ppis.controllers.forms.CloseIncidentForm;
import com.example.ppis.controllers.forms.CreateIncidentForm;
import com.example.ppis.controllers.forms.DistinctIncidentForm;
import com.example.ppis.controllers.forms.EscalationForm;
import com.example.ppis.controllers.forms.GetUserIncidentsForm;
import com.example.ppis.controllers.forms.IncidentResolverForm;
import com.example.ppis.controllers.forms.UnassignedForm;
import com.example.ppis.controllers.viewModels.UserIncidentDetailsViewModel;
import com.example.ppis.services.IncidentService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping(value="/incident")
public class IncidentController {
	@Autowired
	IncidentService incidentService;
	
	@PostMapping(value="/create")
	public ResponseEntity createIncident(@RequestBody @Valid CreateIncidentForm createIncidentForm) {
		try {
			if(incidentService.createIncident(createIncidentForm))
				return ResponseEntity.status(HttpStatus.OK).body(true);
			else 
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping(value="/getAll")
	@ResponseBody
	public ResponseEntity getUserIncidents(@RequestBody @Valid GetUserIncidentsForm getUserIncidentsForm){
		try {
			System.out.println("incidentService.getUserIncidents(getUserIncidentsForm)");
			return ResponseEntity.status(HttpStatus.OK).body(incidentService.getUserIncidents(getUserIncidentsForm));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}
	
	@PostMapping(value="/getIncidentDetail")
	@ResponseBody
	public ResponseEntity getIncidentDetail(@RequestBody @Valid DistinctIncidentForm distinctIncidentForm){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(incidentService.getIncidentDetails(distinctIncidentForm));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping(value="/closeIncident")
	public ResponseEntity closeIncident(@RequestBody @Valid CloseIncidentForm closeIncidentForm){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(true);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping(value="/checkIfClosed")
	public ResponseEntity checkIfClosed(@RequestBody @Valid Long id){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(incidentService.checkIfClosed(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PostMapping(value="/assignIncident")
	public ResponseEntity assignIncident(@RequestBody AssignIncidentForm assignIncidentForm) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(incidentService.assignIncident(assignIncidentForm));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}

	@PostMapping(value="/escalation")
	public ResponseEntity escalation(@RequestBody EscalationForm escalationForm){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(incidentService.escalation(escalationForm));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}

	@PostMapping(value="/unassigned")
	public ResponseEntity unassigned(@RequestBody UnassignedForm unassignedForm){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(incidentService.getUnassignedIncidents(unassignedForm));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}
	
	@PostMapping(value="/assignResolver")
	public ResponseEntity assignResolver(@RequestBody IncidentResolverForm assignIncidentResolverForm){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(incidentService.assignResolver(assignIncidentResolverForm));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}

	@PostMapping(value="/resolve")
	public ResponseEntity resolve(@RequestBody IncidentResolverForm assignIncidentResolverForm){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(incidentService.resolveIncident(assignIncidentResolverForm));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}

	@PostMapping(value="/resolveEscalation")
	public ResponseEntity resolveEscalation(@RequestBody IncidentResolverForm assignIncidentResolverForm){
		try {
			return ResponseEntity.status(HttpStatus.OK).body(incidentService.resolverEscalation(assignIncidentResolverForm));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		}
	}

}
