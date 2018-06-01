package com.example.ppis.events;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.example.ppis.dao.ContactMethodDAO;
import com.example.ppis.dao.DepartmentDAO;
import com.example.ppis.dao.IncidentDAO;
import com.example.ppis.dao.IncidentLogDAO;
import com.example.ppis.dao.RegisteredUserDAO;
import com.example.ppis.dao.RequestDAO;
import com.example.ppis.dao.ServicesDAO;
import com.example.ppis.dao.UserTypeDAO;
import com.example.ppis.models.ContactMethod;
import com.example.ppis.models.Department;
import com.example.ppis.models.Incident;
import com.example.ppis.models.IncidentLog;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.models.Request;
import com.example.ppis.models.Services;
import com.example.ppis.models.UserType;

@Component
public class DatabaseSeeder {
	
	UserTypeDAO userTypeDAO;
	RegisteredUserDAO registeredUserDAO;
	ServicesDAO servicesDAO;
	DepartmentDAO departmentDao ;
	RequestDAO requestDao ; 
	ContactMethodDAO contactMethodDao ; 
	IncidentDAO incidentDAO;
	IncidentLogDAO incidentLogDAO;

	@Autowired
	public void setIncidentLogDAO(IncidentLogDAO incidentLogDAO) {
		this.incidentLogDAO = incidentLogDAO;
	}

	@Autowired
	public void setIncidentDAO(IncidentDAO incidentDAO) {
		this.incidentDAO = incidentDAO;
	}

	@Autowired
	public void setServicesDAO(ServicesDAO servicesDAO) {
		this.servicesDAO = servicesDAO;
	}
	
	@Autowired
	public void setUserTypeDAO(UserTypeDAO userTypeDAO) {
		this.userTypeDAO = userTypeDAO;
	}
	
	@Autowired
	public void setRegisteredUserDAO(RegisteredUserDAO registeredUserDAO) {
		this.registeredUserDAO = registeredUserDAO;
	}
	@Autowired
	public void setDepartmentDAO(DepartmentDAO departmentDao)	{
		this.departmentDao = departmentDao ; 
	}
	@Autowired
	public void setRequestDAO(RequestDAO requestDao)	{
		this.requestDao = requestDao ; 
	}
	@Autowired
	public void setContactMethodDAO(ContactMethodDAO contactMethodDao)	{
		this.contactMethodDao = contactMethodDao ; 
	}
	
	
	@Autowired
	public DatabaseSeeder(UserTypeDAO userTypeDAO,
			RegisteredUserDAO registeredUserDAO) {
	}
	
	@EventListener
	public void seed(ContextRefreshedEvent event) {
		DepartmentTableSeed() ;
		UserTypeTableSeed();
		ContactMethodTableSeed() ; 
		RegisteredUserTableSeed();
		ServicesTableSeed();
		RequestsTableSeed() ;
		IncidentTableSeed();
	}
	
	private void RequestsTableSeed()	{
		if(!this.requestDao.existsById(0))	{
			
			Request r = new Request(this.registeredUserDAO.findUserByUsername("hamster")
					, this.contactMethodDao.getContactMethodByName("email")
					, "Neki titl 1"
					, "Neki opis 1"
					, 5
					, this.registeredUserDAO.findUserByUsername("hamster").getEmail()) ;
			this.requestDao.create(r) ;
			
			r = new Request(this.registeredUserDAO.findUserByUsername("clarkson")
					, this.contactMethodDao.getContactMethodByName("email")
					, "Neki titl 2"
					, "Neki opis 2"
					, 6
					, this.registeredUserDAO.findUserByUsername("clarkson").getEmail()) ;
			this.requestDao.create(r) ;

			r = new Request(this.registeredUserDAO.findUserByUsername("captainSlow")
					, this.contactMethodDao.getContactMethodByName("email")
					, "Neki titl 3"
					, "Neki opis 3"
					, 9
					, this.registeredUserDAO.findUserByUsername("captainSlow").getEmail()) ;
			this.requestDao.create(r) ;	
			
			r = new Request(this.registeredUserDAO.findUserByUsername("hamster")
					, this.contactMethodDao.getContactMethodByName("email")
					, "Bice dodijeljen departmentu 1"
					, "Neki opis 4"
					, 5
					, this.registeredUserDAO.findUserByUsername("hamster").getEmail()) ;
			this.requestDao.create(r) ;
			
			r = new Request(this.registeredUserDAO.findUserByUsername("clarkson")
					, this.contactMethodDao.getContactMethodByName("email")
					, "Bice dodijeljen departmentu 2"
					, "Neki opis 5"
					, 6
					, this.registeredUserDAO.findUserByUsername("clarkson").getEmail()) ;
			this.requestDao.create(r) ;

			r = new Request(this.registeredUserDAO.findUserByUsername("captainSlow")
					, this.contactMethodDao.getContactMethodByName("email")
					, "Bice dodijeljen resolveru 1"
					, "Neki opis 6"
					, 9
					, this.registeredUserDAO.findUserByUsername("captainSlow").getEmail()) ;
			this.requestDao.create(r) ;	

			r = new Request(this.registeredUserDAO.findUserByUsername("clarkson")
					, this.contactMethodDao.getContactMethodByName("email")
					, "Bice dodijeljen resolveru 2"
					, "Neki opis 5"
					, 6
					, this.registeredUserDAO.findUserByUsername("clarkson").getEmail()) ;
			this.requestDao.create(r) ;

			r = new Request(this.registeredUserDAO.findUserByUsername("captainSlow")
					, this.contactMethodDao.getContactMethodByName("email")
					, "Bice dodijeljen resolveru 3"
					, "Neki opis 6"
					, 9
					, this.registeredUserDAO.findUserByUsername("captainSlow").getEmail()) ;
			this.requestDao.create(r) ;	

		}
	}

	private void DepartmentTableSeed()	{
		if(!this.departmentDao.existsById(0))	{
			Department department = new Department() ;
			department.setDepartmentName("Administracija");
			this.departmentDao.create(department) ;
			
			department = new Department() ; 
			department.setDepartmentName("Odjel1");
			this.departmentDao.create(department) ;
			
			department = new Department() ; 
			department.setDepartmentName("Odjel2");
			this.departmentDao.create(department) ;
			
			department = new Department() ; 
			department.setDepartmentName("Odjel3");
			this.departmentDao.create(department) ;
			
		}
	}
	
	private void ContactMethodTableSeed()	{
		if(!this.contactMethodDao.existsById(0))	{
			ContactMethod cm = new ContactMethod() ; 
			cm.setContactMethodName("phone");
			this.contactMethodDao.create(cm) ; 
			
			cm = new ContactMethod(); 
			cm.setContactMethodName("email");
			this.contactMethodDao.create(cm) ;
		}
	}
	
	private void UserTypeTableSeed() {
		if(userTypeDAO.count() == 0) {
			
			UserType ut = new UserType();
			ut.setTypeName("Korisnik");
			ut.setDepartment(null);
			userTypeDAO.create(ut);
			
			ut = new UserType();
			ut.setTypeName("Administrator");
			ut.setDepartment(this.departmentDao.getDepartmentByName("Odjel1"));
			userTypeDAO.create(ut);
			
			ut = new UserType();
			ut.setTypeName("Resolver1");
			ut.setDepartment(this.departmentDao.getDepartmentByName("Odjel1"));
			userTypeDAO.create(ut);
			
			ut = new UserType();
			ut.setTypeName("Resolver2");
			ut.setDepartment(this.departmentDao.getDepartmentByName("Odjel2"));
			userTypeDAO.create(ut);
			
			ut = new UserType();
			ut.setTypeName("Resolver3");
			ut.setDepartment(this.departmentDao.getDepartmentByName("Odjel3"));
			userTypeDAO.create(ut);
			
		}
	}	
	
	private void RegisteredUserTableSeed() {
		
		System.out.println(userTypeDAO.findUserTypeByName("Korisnik"));
		
		if (registeredUserDAO.count() == 0) {
			RegisteredUser ru = new RegisteredUser();
			ru.setEmail("richard.h@email.com");
			ru.setFirstname("Richard");
			ru.setLastname("Hammond");
			ru.setPassword("password");
			ru.setUsername("hamster");
			ru.setAccountNumber("1234123412341234");
			ru.setPhoneNumber("+38761123123");
			ru.setUserType(userTypeDAO.findUserTypeByName("Korisnik"));
			System.out.println("USER TIP JE -----------------     " + ru.getUserType().getTypeName());
			registeredUserDAO.create(ru);
			
			ru = new RegisteredUser();
			ru.setEmail("jeremy.c@email.com");
			ru.setFirstname("Jeremy");
			ru.setLastname("Clarkson");
			ru.setPassword("password");
			ru.setUsername("clarkson");
			ru.setAccountNumber("4321432143214321");
			ru.setPhoneNumber("+38762555888");
			ru.setUserType(userTypeDAO.findUserTypeByName("Korisnik"));
			registeredUserDAO.create(ru);
			
			ru = new RegisteredUser();
			ru.setEmail("james.m@email.com");
			ru.setFirstname("James");
			ru.setLastname("May");
			ru.setPassword("password");
			ru.setUsername("captainSlow");
			ru.setAccountNumber("1111222233334444");
			ru.setPhoneNumber("+38761123123");
			ru.setUserType(userTypeDAO.findUserTypeByName("Korisnik"));
			registeredUserDAO.create(ru);
			
			ru = new RegisteredUser();
			ru.setEmail("admin.a@email.com");
			ru.setFirstname("Admin");
			ru.setLastname("Adminovic");
			ru.setPassword("password");
			ru.setUsername("admin");
			ru.setUserType(userTypeDAO.findUserTypeByName("Administrator"));
			registeredUserDAO.create(ru);
			
			ru = new RegisteredUser();
			ru.setEmail("andy.w@email.com");
			ru.setFirstname("Andy");
			ru.setLastname("Wilman");
			ru.setPassword("password");
			ru.setUsername("andy");
			ru.setUserType(userTypeDAO.findUserTypeByName("Resolver1"));
			registeredUserDAO.create(ru);
		
			ru = new RegisteredUser();
			ru.setEmail("odjel.o@email.com");
			ru.setFirstname("Ime");
			ru.setLastname("Prezime");
			ru.setPassword("password");
			ru.setUsername("odjel");
			ru.setUserType(userTypeDAO.findUserTypeByName("Resolver1"));
			registeredUserDAO.create(ru);

			ru = new RegisteredUser();
			ru.setEmail("korisnik.k@email.com");
			ru.setFirstname("Ime");
			ru.setLastname("Prezime");
			ru.setPassword("password");
			ru.setUsername("korisnik");
			ru.setUserType(userTypeDAO.findUserTypeByName("Korisnik"));
			registeredUserDAO.create(ru);
		}
	}
	
	//also creates registered users services
	private void ServicesTableSeed() {
		if(servicesDAO.count() == 0) {
			
			/*
			 * pregled racuna
			 * otvaranje racuna debitnog
			 * otvaranje racuna kreditni
			 * ugl paketi (student)...
			 * 
			 * */
			
			Services s = new Services();
			s.setServiceName("SMS bankarstvo");
			servicesDAO.create(s);
			
			s = new Services();
			s.setServiceName("Mobilno bankarstvo");
			servicesDAO.create(s);
			s = new Services();
			s.setServiceName("Viber bankarstvo");
			servicesDAO.create(s);
			s = new Services();
			s.setServiceName("Internet bankarstvo");
			servicesDAO.create(s);

			RegisteredUser ru = registeredUserDAO.findUserByUsername("hamster");
			List<Services> ss = Arrays.asList(s);
			
			ru.setServices(ss);
			registeredUserDAO.create(ru);
		}
	}

	private void IncidentTableSeed(){
		if(!this.incidentDAO.existsById(0))	{

			
			Incident i = new Incident();
			i.setRegisteredUser(registeredUserDAO.findUserByUsername("hamster"));
			i.setAdmin(registeredUserDAO.findUserByUsername("admin"));
			i.setResolverUser(registeredUserDAO.findUserByUsername("andy"));
			i.setDepartment(this.departmentDao.getDepartmentByName("Odjel1"));
			i.setContactMethod(contactMethodDao.getContactMethodByName("email"));
			i.setContactInfo("richard.h@email.com");
			i.setTitle("Naslov 1");
			i.setDescription("Opis incidenta 1");
			i.setUrgency(5);
			i.setCreatedDate(new Date());
			i.setResolved(false);
			i.setClosed(false);
			i.setEscalated(true);
			i.setServices(servicesDAO.getServiceByName("Viber bankarstvo"));
			incidentDAO.create(i);
			
			i = new Incident();
			i.setRegisteredUser(registeredUserDAO.findUserByUsername("clarkson"));
			i.setAdmin(registeredUserDAO.findUserByUsername("admin"));
			i.setContactMethod(contactMethodDao.getContactMethodByName("email"));
			i.setContactInfo("james.m@email.com");
			i.setTitle("Naslov 1");
			i.setDescription("Opis incidenta 1");
			i.setUrgency(5);
			i.setCreatedDate(new Date());
			i.setResolved(false);
			i.setClosed(false);
			i.setEscalated(false);
			i.setServices(servicesDAO.getServiceByName("Viber bankarstvo"));
			incidentDAO.create(i);


			//Incident bez admina
			i = new Incident();
			i.setRegisteredUser(registeredUserDAO.findUserByUsername("korisnik"));
			i.setContactMethod(contactMethodDao.getContactMethodByName("email"));
			i.setContactInfo("korisnik.k@email.com");
			i.setTitle("Incident koji nije dodijeljen");
			i.setDescription("Opis incidenta 1");
			i.setUrgency(5);
			i.setCreatedDate(new Date());
			i.setResolved(false);
			i.setClosed(false);
			i.setEscalated(false);
			i.setServices(servicesDAO.getServiceByName("Viber bankarstvo"));
			incidentDAO.create(i);

			i = new Incident();
			i.setRegisteredUser(registeredUserDAO.findUserByUsername("korisnik"));
			i.setAdmin(registeredUserDAO.findUserByUsername("admin"));
			i.setContactMethod(contactMethodDao.getContactMethodByName("email"));
			i.setContactInfo("korisnik.k@email.com");
			i.setTitle("Incidet preuzet od strane admina");
			i.setDescription("Opis incidenta 1");
			i.setUrgency(5);
			i.setCreatedDate(new Date());
			i.setResolved(false);
			i.setClosed(false);
			i.setEscalated(false);
			i.setServices(servicesDAO.getServiceByName("Viber bankarstvo"));
			incidentDAO.create(i);

			i = new Incident();
			i.setRegisteredUser(registeredUserDAO.findUserByUsername("korisnik"));
			i.setAdmin(registeredUserDAO.findUserByUsername("admin"));
			i.setResolverUser(registeredUserDAO.findUserByUsername("odjel"));
			i.setDepartment(this.departmentDao.getDepartmentByName("Odjel1"));
			i.setContactMethod(contactMethodDao.getContactMethodByName("email"));
			i.setContactInfo("korisnik.k@email.com");
			i.setTitle("Incidet preuzet od strane odjela");
			i.setDescription("Opis incidenta 1");
			i.setUrgency(5);
			i.setCreatedDate(new Date());
			i.setResolved(false);
			i.setClosed(false);
			i.setEscalated(false);
			i.setServices(servicesDAO.getServiceByName("Viber bankarstvo"));
			incidentDAO.create(i);

			i = new Incident();
			i.setRegisteredUser(registeredUserDAO.findUserByUsername("korisnik"));
			i.setAdmin(registeredUserDAO.findUserByUsername("admin"));
			i.setResolverUser(registeredUserDAO.findUserByUsername("odjel"));
			i.setDepartment(this.departmentDao.getDepartmentByName("Odjel1"));
			i.setContactMethod(contactMethodDao.getContactMethodByName("email"));
			i.setContactInfo("korisnik.k@email.com");
			i.setTitle("Incidet koji je rijesen");
			i.setDescription("Opis incidenta 1");
			i.setUrgency(5);
			i.setCreatedDate(new Date());
			i.setResolved(true);
			i.setClosed(false);
			i.setEscalated(false);
			i.setServices(servicesDAO.getServiceByName("Viber bankarstvo"));
			incidentDAO.create(i);

			IncidentLog il = new IncidentLog();
			il.setIncident(i);
			il.setResolveDate(new Date());
			incidentLogDAO.create(il);

			i = new Incident();
			i.setRegisteredUser(registeredUserDAO.findUserByUsername("korisnik"));
			i.setAdmin(registeredUserDAO.findUserByUsername("admin"));
			i.setResolverUser(registeredUserDAO.findUserByUsername("odjel"));
			i.setDepartment(this.departmentDao.getDepartmentByName("Odjel1"));
			i.setContactMethod(contactMethodDao.getContactMethodByName("email"));
			i.setContactInfo("korisnik.k@email.com");
			i.setTitle("Incidet koji je zavrsen");
			i.setDescription("Opis incidenta 1");
			i.setUrgency(5);
			i.setCreatedDate(new Date());
			i.setResolved(false);
			i.setClosed(true);
			i.setEscalated(false);
			i.setServices(servicesDAO.getServiceByName("Viber bankarstvo"));
			incidentDAO.create(i);

			il = new IncidentLog();
			il.setIncident(i);
			il.setResolveDate(new Date());
			incidentLogDAO.create(il);

			i = new Incident();
			i.setRegisteredUser(registeredUserDAO.findUserByUsername("korisnik"));
			i.setAdmin(registeredUserDAO.findUserByUsername("admin"));
			i.setResolverUser(registeredUserDAO.findUserByUsername("odjel"));
			i.setDepartment(this.departmentDao.getDepartmentByName("Odjel1"));
			i.setContactMethod(contactMethodDao.getContactMethodByName("email"));
			i.setContactInfo("korisnik.k@email.com");
			i.setTitle("Incidet koji je eskaliran");
			i.setDescription("Opis incidenta 1");
			i.setUrgency(5);
			i.setCreatedDate(new Date());
			i.setResolved(false);
			i.setClosed(false);
			i.setEscalated(true);
			i.setServices(servicesDAO.getServiceByName("Viber bankarstvo"));
			incidentDAO.create(i);

			i = new Incident();
			i.setRegisteredUser(registeredUserDAO.findUserByUsername("korisnik"));
			i.setAdmin(registeredUserDAO.findUserByUsername("admin"));
			i.setResolverUser(null);
			i.setDepartment(this.departmentDao.getDepartmentByName("Odjel1"));
			i.setContactMethod(contactMethodDao.getContactMethodByName("email"));
			i.setContactInfo("korisnik.k@email.com");
			i.setTitle("Incidet koji je eskaliran");
			i.setDescription("Opis incidenta 1");
			i.setUrgency(5);
			i.setCreatedDate(new Date());
			i.setResolved(false);
			i.setClosed(false);
			i.setEscalated(true);
			i.setServices(servicesDAO.getServiceByName("Viber bankarstvo"));
			incidentDAO.create(i);

		}
	}
}
