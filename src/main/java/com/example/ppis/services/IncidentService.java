package com.example.ppis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.qos.logback.core.joran.conditional.ElseAction;

import javax.servlet.ServletException;

import com.example.ppis.controllers.forms.CreateIncidentForm;
import com.example.ppis.dao.ContactMethodDAO;
import com.example.ppis.dao.DepartmentDAO;
import com.example.ppis.dao.IncidentDAO;
import com.example.ppis.dao.RegisteredUserDAO;
import com.example.ppis.dao.ServicesDAO;
import com.example.ppis.models.ContactMethod;
import com.example.ppis.models.Department;
import com.example.ppis.models.Incident;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.models.Services;

@Service
public class IncidentService {
	
	IncidentDAO incidentDAO; 
	RegisteredUserDAO registeredUserDAO;
	ServicesDAO serviceDAO;
	ContactMethodDAO contactMethodDAO;

	@Autowired
	public void setContactMethodDAO(ContactMethodDAO contactMethodDAO) {
		this.contactMethodDAO = contactMethodDAO;
	}

	@Autowired
	public void setServiceDAO(ServicesDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}

	@Autowired
	public void setRegisteredUserDAO(RegisteredUserDAO registeredUserDAO) {
		this.registeredUserDAO = registeredUserDAO;
	}

	@Autowired
	public void setIncidentDAO(IncidentDAO incidentDAO)	{
		this.incidentDAO = incidentDAO;
	}

	public Boolean createIncident(CreateIncidentForm createIncidentForm) throws ServletException{

		try {
			if(createIncidentForm.getContactMethodName() == null || createIncidentForm.getContactMethodName().isEmpty() == true)
				throw new ServletException("Nacin kontaktiranja mora biti definiran.");
			if(createIncidentForm.getDescription() == null || createIncidentForm.getDescription().isEmpty() == true)
				throw new ServletException("Opis incidenta nije popunjen.");
			if(createIncidentForm.getServiceName() == null || createIncidentForm.getServiceName().isEmpty() == true)
				throw new ServletException("Servis mora biti specificiran.");
			if(createIncidentForm.getTitle() == null || createIncidentForm.getTitle().isEmpty() == true)
				throw new ServletException("Naslov incident poruke mora biti specificiran.");
			if(createIncidentForm.getUrgency() == null || createIncidentForm.getUrgency() < 0)
				throw new ServletException("Hitnost rjesavanja incidenta mora biti specificiran.");
			if(createIncidentForm.getUsername() == null || createIncidentForm.getUsername().isEmpty())
				throw new ServletException("Korisnicko ime nije specificirano.");
				
			RegisteredUser registeredUser = registeredUserDAO.findUserByUsername(createIncidentForm.getUsername());
			if(registeredUser == null)
				throw new ServletException("Korisnik sa korisnickim imenom " + createIncidentForm.getUsername() + " ne postoji.");

			Services service = serviceDAO.getServiceByName(createIncidentForm.getServiceName());
			if(service == null)
				throw new ServletException("Servis s imenom " + createIncidentForm.getServiceName() + " ne postoji.");

			ContactMethod contactMethod = contactMethodDAO.getContactMethodByName(createIncidentForm.getContactMethodName());
			if(contactMethod == null)
				throw new ServletException("Nacin kontaktiranja nije poznat.");
			String contactInfo = "";
			if(contactMethod.getContactMethodName() == "email")
				contactInfo = registeredUser.getEmail();
			else if(contactMethod.getContactMethodName() == "phone")
				contactInfo = registeredUser.getPhoneNumber();
			else
				throw new ServletException("Nacin kontaktiranja nije poznat.");
			Incident incident = new Incident(registeredUser, 
											contactMethod, 
											service, 
											null, 
											createIncidentForm.getTitle(), 
											createIncidentForm.getDescription(), 
											null, 
											createIncidentForm.getUrgency(), 
											contactInfo);
			return true;
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}

	public Incident getIncidentByID(Long id){
		return incidentDAO.getIncidentByID(id);
	}
}
