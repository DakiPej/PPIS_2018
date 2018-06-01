package com.example.ppis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;

import com.example.ppis.controllers.viewModels.AdminIncidentDetailsViewModel;
import com.example.ppis.controllers.viewModels.AdminIncidentsViewModel;
import com.example.ppis.controllers.viewModels.DepartmentDetailsViewModel;
import com.example.ppis.controllers.viewModels.DepartmentIncidentsViewModel;
import com.example.ppis.controllers.viewModels.UnassignedIncidentViewModel;
import com.example.ppis.controllers.viewModels.UserIncidentDetailsViewModel;
import com.example.ppis.controllers.viewModels.UserIncidentsViewModel;
import com.example.ppis.controllers.forms.AssignIncidentForm;
import com.example.ppis.controllers.forms.CloseIncidentForm;
import com.example.ppis.controllers.forms.CreateIncidentForm;
import com.example.ppis.controllers.forms.DistinctIncidentForm;
import com.example.ppis.controllers.forms.EscalationForm;
import com.example.ppis.controllers.forms.GetUserIncidentsForm;
import com.example.ppis.controllers.forms.IncidentResolverForm;
import com.example.ppis.controllers.forms.UnassignedForm;
import com.example.ppis.dao.ContactMethodDAO;
import com.example.ppis.dao.DepartmentDAO;
import com.example.ppis.dao.IncidentDAO;
import com.example.ppis.dao.IncidentLogDAO;
import com.example.ppis.dao.RegisteredUserDAO;
import com.example.ppis.dao.ServicesDAO;
import com.example.ppis.dao.UserTypeDAO;
import com.example.ppis.models.ContactMethod;
import com.example.ppis.models.Department;
import com.example.ppis.models.Incident;
import com.example.ppis.models.IncidentLog;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.models.Services;
import com.example.ppis.models.UserType;

@Service
public class IncidentService {

	IncidentDAO incidentDao;
	RegisteredUserDAO registeredUserDao;
	ServicesDAO serviceDAO;
	ContactMethodDAO contactMethodDAO;
	DepartmentDAO departmentDao;
	IncidentLogDAO incidentLogDAO;
	UserTypeDAO userTypeDAO;

	@Autowired
	public void setUserTypeDAO(UserTypeDAO userTypeDAO) {
		this.userTypeDAO = userTypeDAO;
	}

	@Autowired
	public void setIncidentLogDAO(IncidentLogDAO incidentLogDAO) {
		this.incidentLogDAO = incidentLogDAO;
	}

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
	public void setIncidentDAO(IncidentDAO incidentDao) {
		this.incidentDao = incidentDao;
	}

	@Autowired
	public void setDepartmentDao(DepartmentDAO departmentDao) {
		this.departmentDao = departmentDao;
	}
	/*
	 * private boolean validateNewIncident(String registeredUserUsername , String
	 * contactMethod , String service , String title , String description , Integer
	 * urgency) {
	 * 
	 * if(registeredUserUsername.length() <= 0 ||
	 * !this.registeredUserDao.existsByUsername(registeredUserUsername)) throw new
	 * IllegalArgumentException("The user was not specified or does not exists.") ;
	 * 
	 * if(contactMethod.length() == 0 ||
	 * this.contactMethodDAO.existsByContactMethodName(contactMethod)) throw new
	 * IllegalArgumentException("The contact method is unspecified or does not exist."
	 * ) ;
	 * 
	 * if(service.length() == 0 || this.serviceDAO.existsByServiceName(service))
	 * throw new
	 * IllegalArgumentException("The service is unspecified or does not exist.") ;
	 * 
	 * if(title.length() == 0) throw new
	 * IllegalArgumentException("The title is not specified.") ;
	 * 
	 * if(description.length() == 0) throw new
	 * IllegalArgumentException("The description is not specivfied.") ;
	 * 
	 * if(urgency == null || urgency <= 0) throw new
	 * IllegalArgumentException("The urgency is not specified or is a negative number."
	 * ) ;
	 * 
	 * return true ; }
	 * 
	 * public String saveNewIncident(String registeredUserUsername , String
	 * contactMethod , String service , String title , String description , Integer
	 * urgency , String contactInfo) {
	 * 
	 * Incident incident ;
	 * 
	 * try { if(validateNewIncident(registeredUserUsername , contactMethod , title ,
	 * service , description , urgency)) { incident = new
	 * Incident(this.registeredUserDao.findUserByUsername(registeredUserUsername) ,
	 * this.contactMethodDAO.getContactMethodByName(contactMethod) ,
	 * this.serviceDAO.getServiceByName(service) , title , description , urgency ,
	 * contactInfo) ;
	 * 
	 * } } catch (Exception e) { // TODO: handle exception }
	 * 
	 * return "A new incident has been created." ; }
	 */

	private RegisteredUser getUserByUsername(String username) throws ServletException {
		try {
			return registeredUserDao.findUserByUsername(username);

		} catch (Exception e) {
			throw new ServletException("Greska u incident servisu kod dobavljanja korisnika");
		}
	}

	private Services getServiceByName(String serviceName) throws ServletException {
		try {
			return serviceDAO.getServiceByName(serviceName);
		} catch (Exception e) {
			throw new ServletException("Greska u incident servisu kod dobavljanja servisa");
		}
	}

	private ContactMethod getContactMethodByName(String contactMethodName) throws ServletException {
		try {
			return contactMethodDAO.getContactMethodByName(contactMethodName);
		} catch (Exception e) {
			throw new ServletException("Greska u incident servisu kod dobavljanja nacina kontaktiranja");
		}
	}

	public Boolean createIncident(CreateIncidentForm createIncidentForm) throws ServletException {

		try {
			if (createIncidentForm.getContactMethodName() == null
					|| createIncidentForm.getContactMethodName().isEmpty() == true)
				throw new ServletException("Nacin kontaktiranja mora biti definiran.");
			if (createIncidentForm.getDescription() == null || createIncidentForm.getDescription().isEmpty() == true)
				throw new ServletException("Opis incidenta nije popunjen.");
			if (createIncidentForm.getServiceName() == null || createIncidentForm.getServiceName().isEmpty() == true)
				throw new ServletException("Servis mora biti specificiran.");
			if (createIncidentForm.getTitle() == null || createIncidentForm.getTitle().isEmpty() == true)
				throw new ServletException("Naslov incident poruke mora biti specificiran.");
			if (createIncidentForm.getUrgency() == null || createIncidentForm.getUrgency() < 0)
				throw new ServletException("Hitnost rjesavanja incidenta mora biti specificiran.");
			if (createIncidentForm.getUsername() == null || createIncidentForm.getUsername().isEmpty())
				throw new ServletException("Korisnicko ime nije specificirano.");

			RegisteredUser registeredUser = getUserByUsername(createIncidentForm.getUsername());
			if (registeredUser == null)
				throw new ServletException(
						"Korisnik sa korisnickim imenom " + createIncidentForm.getUsername() + " ne postoji.");

			Services service = getServiceByName(createIncidentForm.getServiceName());
			if (service == null)
				throw new ServletException("Servis s imenom " + createIncidentForm.getServiceName() + " ne postoji.");

			ContactMethod contactMethod = getContactMethodByName(createIncidentForm.getContactMethodName());
			if (contactMethod == null)
				throw new ServletException("Nacin kontaktiranja nije poznat.");
			System.out.println(contactMethod.getContactMethodName());
			String contactInfo = "";
			if (contactMethod.getContactMethodName().equals("email"))
				contactInfo = registeredUser.getEmail();
			else if (contactMethod.getContactMethodName().equals("phone"))
				contactInfo = registeredUser.getPhoneNumber();
			else
				throw new ServletException("ovdje"+"Nacin kontaktiranja nije poznat.");

			Incident incident = new Incident(registeredUser, contactMethod, service, createIncidentForm.getTitle(),
					createIncidentForm.getDescription(), createIncidentForm.getUrgency(), contactInfo);
			incidentDao.create(incident);
			return true;
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}
	}

		private String getIncidentStatus(RegisteredUser resolverUser, Boolean resolved, Boolean closed) {
			String status = "svi";
			if (closed == true)
				status = "zatvoren";
			else if (resolved == true)
				status = "rijesen";
			else if (resolverUser != null)
				status = "u obradi";
			else if (resolverUser == null)
				status = "nedodijeljen";
			return status;
		}

	public <T> List<T> getUserIncidents(GetUserIncidentsForm getUserIncidentsForm) throws ServletException {
		try {
			if (getUserIncidentsForm.getUsername() == null || getUserIncidentsForm.getUsername().isEmpty())
				throw new ServletException("Kofisnicko ime nije specificirano");

			RegisteredUser registeredUser = getUserByUsername(getUserIncidentsForm.getUsername());
			if (registeredUser.getUserType().getTypeName().equals("Korisnik") == true) {
				List<Incident> incidents = incidentDao.getIncidentsByRegisteredUser(registeredUser);
				List<UserIncidentsViewModel> userIncidents = new ArrayList<UserIncidentsViewModel>();
				for (Incident i : incidents) {
					if (getUserIncidentsForm.getFilter().equals("svi") || getUserIncidentsForm.getFilter()
							.equals(getIncidentStatus(i.getResolverUser(), i.getResolved(), i.getClosed())))
						userIncidents.add(new UserIncidentsViewModel(i.getId(), i.getTitle(), i.getCreatedDate(),i.getUrgency(), null,
								i.getClosedDate(), i.getServices().getServiceName(),
								getIncidentStatus(i.getResolverUser(), i.getResolved(), i.getClosed())));
				}
				return (List<T>) userIncidents;
			} else if (registeredUser.getUserType().getTypeName().equals("Administrator") == true) {
				List<Incident> incidents = incidentDao.getIncidentsByAdmin(registeredUser);
				
				List<AdminIncidentsViewModel> userIncidents = new ArrayList<AdminIncidentsViewModel>();
/*
				AdminIncidentsViewModel adminModels = new AdminIncidentsViewModel();
				List<AdminIncidentsViewModel> userIncidents = adminModels.converToVMs(incidents);
*/

				for (Incident i : incidents) {
					if (getUserIncidentsForm.getFilter().equals("svi") || getUserIncidentsForm.getFilter()
							.equals(getIncidentStatus(i.getResolverUser(), i.getResolved(), i.getClosed()))) {

						String resolverUsername = "";
						String departmentName = "";
						if (i.getResolverUser() != null)
							resolverUsername = i.getResolverUser().getUsername();
						if (i.getDepartment() != null)
							departmentName = i.getDepartment().getDepartmentName();

						AdminIncidentsViewModel adminIncidentsViewModel = new AdminIncidentsViewModel(
							i.getId(),
							i.getTitle(), 
							i.getRegisteredUser().getUsername(), 
							resolverUsername, 
							i.getUrgency(),
							i.getPriority(), 
							i.getCreatedDate(), 
							null, 
							i.getClosedDate(),
							i.getServices().getServiceName(),
							getIncidentStatus(i.getResolverUser(), i.getResolved(), i.getClosed()), 
							departmentName,
							i.getEscalated());
								
								//AdminIncidentsViewModel adminIncidentsViewModel = new AdminIncidentsViewModel();
						userIncidents.add(adminIncidentsViewModel);
							}
				}

				return (List<T>) userIncidents;
			} else {
				List<DepartmentIncidentsViewModel> userIncidents = new ArrayList<DepartmentIncidentsViewModel>();
				List<Incident> incidents = incidentDao.getIncidentsByResolver(registeredUser);
				for (Incident i : incidents) {
					if (getUserIncidentsForm.getFilter().equals("svi") || getUserIncidentsForm.getFilter()
							.equals(getIncidentStatus(i.getResolverUser(), i.getResolved(), i.getClosed()))) {

						DepartmentIncidentsViewModel departmentIncidentsViewModel = new DepartmentIncidentsViewModel(
								i.getId(), i.getRegisteredUser().getUsername(),i.getTitle(), i.getPriority(), i.getCreatedDate(), null, i.getClosedDate(),
								i.getServices().getServiceName(),
								getIncidentStatus(i.getResolverUser(), i.getResolved(), i.getClosed()),
								i.getEscalated());
						userIncidents.add(departmentIncidentsViewModel);
					}
				}

				return (List<T>) userIncidents;
			}

		} catch (Exception e) {
			System.out.println(e.getMessage() + "ILI JE OVDJE");
			throw e;
		}
	}

	public List<UnassignedIncidentViewModel> getUnassignedIncidents(UnassignedForm unassignedForm) {
		try {
			List<UnassignedIncidentViewModel> unassignedIncident = new ArrayList<UnassignedIncidentViewModel>();
			RegisteredUser ru = registeredUserDao.findUserByUsername(unassignedForm.getUsername());
			List<Incident> incidents = incidentDao
					.getAllByDepartmentNameAndResolverUserIsNull(ru.getUserType().getDepartment());

			for (Incident i : incidents) {
				unassignedIncident.add(new UnassignedIncidentViewModel(i.getId(), i.getTitle(),
						i.getRegisteredUser().getUsername(), i.getPriority(), i.getCreatedDate(),
						i.getServices().getServiceName(), i.getDepartment().getDepartmentName()));
			}

			return unassignedIncident;
		} catch (Exception e) {
			throw e;
		}
	}

	public <T> T getIncidentDetails(DistinctIncidentForm distinctIncidentForm) throws ServletException {
		try {
			Incident incident = incidentDao.getIncidentById(distinctIncidentForm.getId());
			if (incident == null)
				throw new ServletException("Incident ne postoji");

			RegisteredUser registeredUser = registeredUserDao.findUserByUsername(distinctIncidentForm.getUsername());

			if (registeredUser != null) {
				if (registeredUser.getUserType().getTypeName().equals("Korisnik")) {

					UserIncidentDetailsViewModel incidentDetail = new UserIncidentDetailsViewModel(incident.getId(),
							incident.getTitle(), incident.getDescription(), incident.getUrgency(),
							incident.getContactMethod().getContactMethodName(), incident.getCreatedDate(), null,
							incident.getClosedDate(), incident.getServices().getServiceName(), getIncidentStatus(
									incident.getResolverUser(), incident.getResolved(), incident.getClosed()));
					return (T) incidentDetail;
				} else if (registeredUser.getUserType().getTypeName().equals("Administrator")) {
					String resolverUsername = "";
					String departmentName = "";
					if (incident.getDepartment() != null)
						departmentName = incident.getDepartment().getDepartmentName();
					if (incident.getResolverUser() != null)
						resolverUsername = incident.getResolverUser().getUsername();
					AdminIncidentDetailsViewModel incidentDetail = new AdminIncidentDetailsViewModel(incident.getId(),
							incident.getRegisteredUser().getUsername(), resolverUsername, incident.getTitle(),
							incident.getServices().getServiceName(), departmentName, incident.getDescription(),
							incident.getUrgency(), incident.getPriority(), incident.getCreatedDate(), null,
							incident.getClosedDate(), incident.getContactMethod().getContactMethodName(),
							getIncidentStatus(incident.getResolverUser(), incident.getResolved(), incident.getClosed()),
							incident.getEscalated());
					return (T) incidentDetail;
				} else {
					DepartmentDetailsViewModel departmentDetailsViewModel = new DepartmentDetailsViewModel(incident.getId(), incident.getRegisteredUser().getUsername(), incident.getTitle(), incident.getServices().getServiceName(), incident.getDescription(), incident.getPriority(), incident.getCreatedDate(), null, incident.getClosedDate(), getIncidentStatus(incident.getResolverUser(), incident.getResolved(), incident.getClosed()), incident.getEscalated());
					return (T) departmentDetailsViewModel;
				}
			} 
			else return null;

		} catch (Exception e) {
			throw e;
		}
	}

	public void closeIncident(CloseIncidentForm closeIncidentForm) {
		if (closeIncidentForm.getClose() == true) {
			Incident incident = incidentDao.getIncidentById(closeIncidentForm.getId());
			incident.setClosed(true);
			incidentDao.create(incident);
		} else {
			Incident incident = incidentDao.getIncidentById(closeIncidentForm.getId());
			incident.setResolved(false);
			incidentDao.create(incident);
		}
	}

	public Boolean checkIfClosed(Long id) {
		return incidentDao.getIncidentById(id).getClosed();
	}

	public Boolean assignIncident(AssignIncidentForm assignIncidentForm) {

		Incident i = incidentDao.getIncidentById(assignIncidentForm.getId());
		RegisteredUser admin = registeredUserDao.findUserByUsername(assignIncidentForm.getAdminUsername());
		i.setAdmin(admin);
		i.setPriority(assignIncidentForm.getPriority());
		Department d = departmentDao.getDepartmentByName(assignIncidentForm.getDepartmentName());
		i.setDepartment(d);
		incidentDao.create(i);
		return true;
	}

	public Boolean escalation(EscalationForm escalationForm) {
		Incident i = incidentDao.getIncidentById(escalationForm.getId());
		if (escalationForm.getEscalation() == true) {
			Department d = departmentDao.getDepartmentByName(escalationForm.getDepartmentName());
			i.setEscalated(false);
		} else
			i.setEscalated(false);
		
		incidentDao.create(i);

		return true;
	}

	public Boolean assignResolver(IncidentResolverForm assignIncidentResolverForm) throws ServletException {
		try {
			RegisteredUser ru = registeredUserDao.findUserByUsername(assignIncidentResolverForm.getUsername());
			if (ru != null) {
				Incident i = incidentDao.getIncidentById(assignIncidentResolverForm.getId());
				if (i != null) {
					i.setResolverUser(ru);
					incidentDao.create(i);
					return true;
				} else
					throw new ServletException("Incident ne postoji u bazi");

			} else
				throw new ServletException("Korsinik ne postoji u bazi");
		} catch (Exception e) {
			throw e;
		}
	}

	public Boolean resolveIncident(IncidentResolverForm resolveIncident ) throws ServletException{
		try {
			RegisteredUser ru = registeredUserDao.findUserByUsername(resolveIncident.getUsername());
			Incident i = incidentDao.getIncidentById(resolveIncident.getId());

			if(ru == null) throw new ServletException("Korsinik ne postoji");
			else if(i == null) throw new ServletException("Incident ne postoji");
			else if(!i.getResolverUser().getUsername().equals(ru.getUsername())) throw new ServletException("Incident ne pripada korisniku");

			i.setResolved(true);
			incidentDao.create(i);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	public Boolean resolverEscalation(IncidentResolverForm incidentEscalation) throws ServletException{
		Incident i = incidentDao.getIncidentById(incidentEscalation.getId());
		i.setEscalated(true);
		incidentDao.create(i);
		return true;
	}
	
	public String resolveInc(String username, long incidentId, boolean resolved)	{
		try {
			if(username == null || username.length() == 0 || !this.registeredUserDao.existsByUsername(username))
				throw new IllegalArgumentException("The user was not specified or does not exist.") ; 
			if(incidentId< 1 || !this.incidentDao.existsById(incidentId))
				throw new IllegalArgumentException("The incident id must be positive or the incident does not exist.") ;
			
			RegisteredUser user = this.registeredUserDao.findUserByUsername(username) ; 
			Incident incident = this.incidentDao.getIncidentById(incidentId) ; 
			
			if(!incident.getRegisteredUser().equals(user) && !incident.getResolverUser().equals(user))
				throw new IllegalArgumentException("The user does not have the permission to resolve this incident") ; 
			
			incident.setResolved(resolved);
			this.incidentDao.create(incident) ; 
			
			if(resolved)	
				return "The user has successfully resolved the incident." ;
			return "The user has successfully marked the incident as not resolved." ; 
			
		} catch (Exception e) {
			throw e ; 
		}
	}
	
	public String closeInc(String username, long incidentId)	{
		try {
			if(username == null || username.length() == 0 || !this.registeredUserDao.existsByUsername(username))
				throw new IllegalArgumentException("The user was not specified or does not exist.") ; 
			if(incidentId< 1 || !this.incidentDao.existsById(incidentId))
				throw new IllegalArgumentException("The incident id must be positive or the incident does not exist.") ;
			
			RegisteredUser user = this.registeredUserDao.findUserByUsername(username) ; 
			Incident incident = this.incidentDao.getIncidentById(incidentId) ; 
			
			if(!incident.getRegisteredUser().equals(user))
				throw new IllegalArgumentException("The user did not create the incident.") ;
			incident.setClosed(true);
			incident.setClosedDate(new Date());
			
			this.incidentDao.create(incident) ; 
			
			return "The incident was successfully closed." ; 
			
		} catch (Exception e) {
			throw e ; 
		}
	}
	
	public String resolverPick(String username, long incidentId)	{
		try {
			if(username == null || username.length() == 0 || !this.registeredUserDao.existsByUsername(username))
				throw new IllegalArgumentException("The user was not specified or does not exist.") ; 
			if(incidentId< 1 || !this.incidentDao.existsById(incidentId))
				throw new IllegalArgumentException("The incident id must be positive or the incident does not exist.") ;
			
			RegisteredUser user = this.registeredUserDao.findUserByUsername(username) ; 
			Incident incident = this.incidentDao.getIncidentById(incidentId) ; 
			
			//if(!user.getUserType().equals("Resolver"))
			//	throw new IllegalArgumentException("The user can't pick this incident.") ; 
			
			
			//if(!this.registeredUserDao.getUserDepartment(
			//		user.getUsername(), user.getUserType())
			//		.equals(incident.getDepartment()))
			//	throw new IllegalArgumentException("The user does not belong to this department.") ; 
				System.out.println("TU SAM");
			incident.setResolverUser(user);
				
			this.incidentDao.create(incident) ; 
			
			return "The incident was successfully picked by the resolver" ;
			
		} catch (Exception e) {
			throw e ; 
		}
	}

	public List<Incident> adminUnassigned(){
		try {
			List<Incident> incidents = incidentDao.getAllByAdminIsNull();
			return incidents;
		} catch (Exception e) {
			throw e;
			//TODO: handle exception
		
		}
	}
	public List<Incident> getAllUnassignedByDepartments(String username)	{
		try {
			if(username == null || username.length() == 0 || !this.registeredUserDao.existsByUsername(username)) 
				throw new IllegalArgumentException("The user is not specified or does not exist.") ;
			RegisteredUser admin = this.registeredUserDao.findUserByUsername(username) ;
			if(!admin.getUserType().getTypeName().equals("Administrator"))
				throw new IllegalArgumentException("The user has not the permission to view the items.") ; 
			return this.incidentDao.getIncidentsByDepartment(null) ; 
		} catch (Exception e) {
			throw e ; 
		}
	}
	
	public List<Incident> getUnassignedByResolvers(String username)	{
		try {
			Department department = this.registeredUserDao.getUserDepartment(username, this.registeredUserDao.findUserByUsername(username).getUserType()) ; 
			return this.incidentDao.getAllByDepartmentNameAndResolverUserIsNull(department) ; 
		} catch (Exception e) {
			throw e ; 
		}
	}
}
