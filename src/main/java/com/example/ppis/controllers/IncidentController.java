package com.example.ppis.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.validation.Valid;

import com.example.ppis.controllers.forms.AssignIncidentForm;
import com.example.ppis.controllers.forms.CloseIncidentForm;
import com.example.ppis.controllers.forms.CreateIncidentForm;
import com.example.ppis.controllers.forms.DistinctIncidentForm;
import com.example.ppis.controllers.forms.EscalationForm;
import com.example.ppis.controllers.forms.GetUserIncidentsForm;
import com.example.ppis.controllers.forms.IncidentResolverForm;
import com.example.ppis.controllers.forms.UnassignedForm;
import com.example.ppis.controllers.viewModels.AdminIncidentDetailsViewModel;
import com.example.ppis.controllers.viewModels.AdminIncidentsViewModel;
import com.example.ppis.controllers.viewModels.DepartmentDetailsViewModel;
import com.example.ppis.controllers.viewModels.DepartmentIncidentsViewModel;
import com.example.ppis.models.Incident;
import com.example.ppis.services.IncidentService;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin
@RestController
@RequestMapping(value="/incident")
public class IncidentController {
	@Autowired
	IncidentService incidentService;
	private AdminIncidentDetailsViewModel adminDetails = new AdminIncidentDetailsViewModel() ; 
	private DepartmentDetailsViewModel departmentDetails = new DepartmentDetailsViewModel() ; 
	private AdminIncidentsViewModel adminModels = new AdminIncidentsViewModel() ; 
	private DepartmentIncidentsViewModel departmentModels = new DepartmentIncidentsViewModel() ; 
	
	@PostMapping(value="/create")
	public ResponseEntity createIncident(@RequestBody @Valid CreateIncidentForm createIncidentForm) {
		try {
			if(incidentService.createIncident(createIncidentForm))
				return ResponseEntity.status(HttpStatus.OK).body(true);
			else 
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(false);
		} catch (Exception e) {
			System.out.println(e.getMessage());
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
	
	@RequestMapping(value="resolve", method=RequestMethod.PUT)
	public ResponseEntity resolveInc(@RequestBody final IncidentStatusUpdate info)	{
		try {
			String response = "" ;
			response = this.incidentService.resolveInc(info.username, info.incidentId, info.resolved) ;
			
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	
	@RequestMapping(value="close", method=RequestMethod.PUT)
	public ResponseEntity closeInc(@RequestBody final IncidentStatusUpdate info)	{
		try {
			String response = "" ; 
			response = this.incidentService.closeInc(info.username, info.incidentId) ; 
			
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}

	@RequestMapping(value="/adminUnassigned", method=RequestMethod.GET)
	public ResponseEntity adminUnassigned()	{
		try {
			return ResponseEntity.status(HttpStatus.OK).body(incidentService.adminUnassigned()) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	
	@RequestMapping(value="/resolverPick", method=RequestMethod.PUT)
	public ResponseEntity resolverPick(@RequestBody final IncidentStatusUpdate info)	{
		try {
			String response = "" ; 
			response = this.incidentService.resolverPick(info.username, info.incidentId) ;
			
			return ResponseEntity.status(HttpStatus.OK).body(response) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	
	@RequestMapping(value="/unassigned_byDepartments/{username}", method=RequestMethod.GET)
	public ResponseEntity getUnassignedByDepartments(@PathVariable("username") String username)	{
		try {
			List<Incident> incidents = this.incidentService.getAllUnassignedByDepartments(username) ;
			List<AdminIncidentsViewModel> vms = adminModels.converToVMs(incidents) ; 
			
			return ResponseEntity.status(HttpStatus.OK).body(vms) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	
	@RequestMapping(value="/unassigned_byResolvers/{username}", method=RequestMethod.GET) 
	public ResponseEntity getUnassignedByResolvers(@PathVariable("username") String username)	{
		try {
			List<Incident> incidents = this.incidentService.getUnassignedByResolvers(username) ; 
			List<DepartmentIncidentsViewModel> vms = departmentModels.convertToVMs(incidents) ; 
			
			return ResponseEntity.status(HttpStatus.OK).body(vms) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
	
	private static class IncidentStatusUpdate {
		String username ; 
		long incidentId ; 
		boolean resolved ; 
	}

}
