package com.example.ppis.controllers.viewModels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.ppis.models.Incident;
import com.example.ppis.models.RegisteredUser;

public class AdminIncidentDetailsViewModel{

    private Long id;
    private String creatorUsername;
    private String resolverUsername;
    private String title;
    private String serviceName;
    private String departmentName;
    private String description;
    private Integer urgency;
    private Integer priority;
    private String createdDate;
    private String lastResolveDate;
    private String closedDate;
    private String contactMethod;
    private String status;
    private Boolean escalation;

    public AdminIncidentDetailsViewModel()	{
    	
    }
    
    public AdminIncidentDetailsViewModel(Long id,
                                String creatorUsername,
                                String resolverUsername,
                                String title,
                                String serviceName,
                                String departmentName,
                                String description,
                                Integer urgency,
                                Integer priority,
                                Date createdDate,
                                Date lastResolveDate,
                                Date closedDate,
                                String contactMethod,
                                String status,
                                Boolean escalation){
    	
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss") ; 

        this.id = id;
        this.creatorUsername = creatorUsername;
        this.resolverUsername = resolverUsername;
        this.title = title;
        this.serviceName = serviceName;
        this.departmentName = departmentName;
        this.description = description;
        this.urgency = urgency;
        this.priority = priority;
        if (createdDate != null) this.createdDate = df.format(createdDate);
        else this.createdDate = "";
        //this.createdDate =  df.format(createdDate);
        
        if (lastResolveDate != null) this.lastResolveDate = df.format(lastResolveDate);
        else this.closedDate = "";
        //this.lastResolveDate = null; //df.format(lastResolveDate);
        
        if (closedDate != null) this.closedDate = df.format(closedDate);
        else this.closedDate = "";
        //this.closedDate = df.format(lastResolveDate);
        this.contactMethod = contactMethod;
        this.status = status;
        this.escalation = escalation;
    }

    public String getIncidentStatus(RegisteredUser resolverUser, Boolean resolved, Boolean closed) {
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
    
    public AdminIncidentDetailsViewModel converToVM(Incident i)	{
    	AdminIncidentDetailsViewModel vm = new AdminIncidentDetailsViewModel(
    											i.getId()
    											, i.getRegisteredUser().getUsername()
    											, i.getResolverUser().getUsername()
    											, i.getTitle()
    											, i.getServices().getServiceName()
    											, i.getDepartment().getDepartmentName()
    											, i.getDescription()
    											, i.getUrgency()
    											, i.getPriority()
    											, i.getCreatedDate()
    											, null
    											, i.getClosedDate()
    											, i.getContactMethod().getContactMethodName()
    											, getIncidentStatus(i.getResolverUser(), i.getResolved(), i.getClosed())
    											, i.getEscalated()
    											) ;
    	return vm ; 
    }
    
    public List<AdminIncidentDetailsViewModel> convertToVMs(List<Incident> incidents)	{
    	List<AdminIncidentDetailsViewModel> vms = new ArrayList<>() ; 
    	
    	for(int index = 0 ; index < incidents.size() ; index++)	{
    		Incident i = incidents.get(index) ; 
    		
    		AdminIncidentDetailsViewModel vm = new AdminIncidentDetailsViewModel(
    												i.getId()
    												, i.getRegisteredUser().getUsername()
    												, i.getResolverUser().getUsername()
    												, i.getTitle()
    												, i.getServices().getServiceName()
    												, i.getDepartment().getDepartmentName()
    												, i.getDescription()
    												, i.getUrgency()
    												, i.getPriority()
    												, i.getCreatedDate()
    												, null
    												, i.getClosedDate()
    												, i.getContactMethod().getContactMethodName()
    												, getIncidentStatus(i.getResolverUser(), i.getResolved(), i.getClosed())
    												, i.getEscalated()) ;
    		vms.add(vm) ; 
    	}
    	
    	return vms ; 			
    }

    public String getClosedDate() {
        return closedDate;
    }
    public String getContactMethod() {
        return contactMethod;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public String getCreatorUsername() {
        return creatorUsername;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public String getDescription() {
        return description;
    }
    public Boolean getEscalation() {
        return escalation;
    }
    public Long getId() {
        return id;
    }
    public String getLastResolveDate() {
        return lastResolveDate;
    }
    public Integer getPriority() {
        return priority;
    }
    public String getResolverUsername() {
        return resolverUsername;
    }
    public String getServiceName() {
        return serviceName;
    }
    public String getStatus() {
        return status;
    }
    public String getTitle() {
        return title;
    }
    public Integer getUrgency() {
        return urgency;
    }
    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }
    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setEscalation(Boolean escalation) {
        this.escalation = escalation;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setLastResolveDate(String lastResolveDate) {
        this.lastResolveDate = lastResolveDate;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public void setResolverUsername(String resolverUsername) {
        this.resolverUsername = resolverUsername;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
    }
}