package com.example.ppis.controllers.viewModels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.ppis.models.Incident;
import com.example.ppis.models.RegisteredUser;

public class DepartmentDetailsViewModel{
    private Long id;
    private String creatorUsername;
    private String title;
    private String serviceName;
    private String description;
    private Integer priority;
    private String createdDate;
    private String lastResolveDate;
    private String closedDate;
    private String status;
    private Boolean escalation;

    public DepartmentDetailsViewModel()	{
    	
    }
    public DepartmentDetailsViewModel(Long id,
                                String adminUsername,
                                String title,
                                String serviceName,
                                String description,
                                Integer priority,
                                Date createdDate,
                                Date lastResolveDate,
                                Date closedDate,
                                String status,
                                Boolean escalation){
    	
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss") ; 
    	
    	this.creatorUsername=adminUsername;
        this.id = id;
        this.title = title;
        this.serviceName = serviceName;
        this.description = description;
        this.priority = priority;
        if (createdDate != null) this.createdDate = df.format(createdDate);
        else this.createdDate = "";
        //this.createdDate =  df.format(createdDate);
        
        if (lastResolveDate != null) this.lastResolveDate = df.format(lastResolveDate);
        else this.lastResolveDate = "";
        //this.lastResolveDate = null; //df.format(lastResolveDate);
        
        if (closedDate != null) this.closedDate = df.format(closedDate);
        else this.closedDate = "";
        //this.closedDate = df.format(lastResolveDate);
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
    
    public DepartmentDetailsViewModel convertToVM(Incident i)	{
    	DepartmentDetailsViewModel vm = new DepartmentDetailsViewModel(
    											i.getId()
    											, i.getAdmin().getUsername()
    											, i.getTitle()
    											, i.getServices().getServiceName()
    											, i.getDescription()
    											, i.getPriority()
    											, i.getCreatedDate()
    											, null
    											, i.getClosedDate()
    											, getIncidentStatus(i.getResolverUser(), i.getResolved(), i.getClosed())
    											, i.getEscalated()
    											) ;
    	return vm ; 
    }
    
    public List<DepartmentDetailsViewModel> convertToVMs(List<Incident> incidents)	{
    	List<DepartmentDetailsViewModel> vms = new ArrayList<>() ; 
    	
    	for(int index = 0 ; index < incidents.size() ; index++)	{
    		Incident i = incidents.get(index) ; 
    		
    		DepartmentDetailsViewModel vm = new DepartmentDetailsViewModel(
    												i.getId()
    												, i.getAdmin().getUsername()
    												, i.getTitle()
    												, i.getServices().getServiceName()
    												, i.getDescription()
    												, i.getPriority()
    												, i.getCreatedDate()
    												, null
    												, i.getClosedDate()
    												, getIncidentStatus(i.getResolverUser(), i.getResolved(), i.getClosed())
    												, i.getEscalated()
    												) ;
    		vms.add(vm) ; 
    	}
    	
    	return vms ; 
    }
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatorUsername() {
		return creatorUsername;
	}

	public void setCreatorUsername(String adminUsername) {
		this.creatorUsername = adminUsername;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getLastResolveDate() {
		return lastResolveDate;
	}

	public void setLastResolveDate(String lastResolveDate) {
		this.lastResolveDate = lastResolveDate;
	}

	public String getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getEscalation() {
		return escalation;
	}

	public void setEscalation(Boolean escalation) {
		this.escalation = escalation;
	}
}