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
	
	IncidentDAO incidentDao; 
	RegisteredUserDAO registeredUserDao;
	ServicesDAO serviceDAO;
	ContactMethodDAO contactMethodDAO;
	DepartmentDAO departmentDao; 

	@Autowired
	public void setContactMethodDAO(ContactMethodDAO contactMethodDAO) {
		this.contactMethodDAO = contactMethodDAO;
	}

	@Autowired
	public void setServiceDAO(ServicesDAO serviceDAO) {
		this.serviceDAO = serviceDAO;
	}

	@Autowired
	public void setregisteredUserDao(RegisteredUserDAO registeredUserDao) {
		this.registeredUserDao = registeredUserDao;
	}

	@Autowired
	public void setIncidentDAO(IncidentDAO incidentDao)	{
		this.incidentDao = incidentDao;
	}
	
	@Autowired
	public void setDepartmentDao(DepartmentDAO departmentDao)	{
		this.departmentDao = departmentDao ; 
	}
	
	private boolean validateNewIncident(String registeredUserUsername
			, String contactMethod
			, String service
			, String title
			, String description
			, Integer urgency)	{
		
		if(registeredUserUsername.length() <= 0 || !this.registeredUserDao.existsByUsername(registeredUserUsername))
			throw new IllegalArgumentException("The user was not specified or does not exists.") ;
		
		if(contactMethod.length() == 0 || this.contactMethodDAO.existsByContactMethodName(contactMethod))
			throw new IllegalArgumentException("The contact method is unspecified or does not exist.") ;
		if(service.length() == 0 || this.serviceDAO.existsByServiceName(service))
			throw new IllegalArgumentException("The service is unspecified or does not exist.") ;
		
		if(title.length() == 0)
			throw new IllegalArgumentException("The title is not specified.") ;
		
		if(description.length() == 0)
			throw new IllegalArgumentException("The description is not specivfied.") ; 
		
		if(urgency == null || urgency <= 0)
			throw new IllegalArgumentException("The urgency is not specified or is a negative number.") ; 
			
		return true ; 
	}
	
	public String saveNewIncident(String registeredUserUsername
			, String contactMethod
			, String service
			, String title
			, String description
			, Integer urgency
			, String contactInfo)	{
		
		Incident incident ; 
		
		try {
			if(validateNewIncident(registeredUserUsername
					, contactMethod
					, title
					, service
					, description 
					, urgency))	{
				incident = new Incident(this.registeredUserDao.findUserByUsername(registeredUserUsername)
						, this.contactMethodDAO.getContactMethodByName(contactMethod)
						, this.serviceDAO.getServiceByName(service)
						, title
						, description
						, urgency
						, contactInfo) ;
						
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return "A new incident has been created." ; 
	}
	
	
	
	
	
	
	
	/*public Boolean createIncident(CreateIncidentForm createIncidentForm) throws ServletException{

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
				
			RegisteredUser registeredUser = registeredUserDao.findUserByUsername(createIncidentForm.getUsername());
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
	}*/
}
