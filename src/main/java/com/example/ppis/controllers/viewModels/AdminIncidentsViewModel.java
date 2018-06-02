package com.example.ppis.controllers.viewModels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.ppis.models.Incident;
import com.example.ppis.models.RegisteredUser;

public class AdminIncidentsViewModel{
    private Long id;
    private String title;
    private String creatorUsername;
    private String resolverUsername;
    private Integer urgency;
    private Integer priority;
    private String createdDate;
    private String serviceName;
    private String lastResolveDate;
    private String closedDate;
    private String status;
    private String departmentName;
    private Boolean escalation;
    
    public AdminIncidentsViewModel(){

    }

    public AdminIncidentsViewModel(Long id,
                                        String title,
                                        String creatorUsername,
                                        String resolverUsername,
                                        Integer urgency,
                                        Integer priority,
                                        Date createdDate,
                                        Date lastResolveDate,
                                        Date closedDate,
                                        String serviceName,
                                        String status,
                                        String departmentName,
                                        Boolean escalation){
    	
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss") ;
    	
        this.id = id;
        this.title = title;
        this.urgency = urgency;

        if (createdDate != null) this.createdDate = df.format(createdDate);
        else this.createdDate = "";
        //this.createdDate =  df.format(createdDate);
        
        if (lastResolveDate != null) this.lastResolveDate = df.format(lastResolveDate);
        else this.lastResolveDate = "";
        //this.lastResolveDate = null; //df.format(lastResolveDate);
        
        if (closedDate != null) this.closedDate = df.format(closedDate);
        else this.closedDate = "";
        //this.closedDate = df.format(lastResolveDate);

        this.serviceName = serviceName;
        this.status = status;
        this.creatorUsername = creatorUsername;
        this.resolverUsername = resolverUsername;
        this.priority = priority;
        this.departmentName = departmentName;
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
    
    public List<AdminIncidentsViewModel> converToVMs(List<Incident> incidents)	{
    	List<AdminIncidentsViewModel> incidentVMs = new ArrayList<>() ; 
    	
    	for(int index = 0 ; index < incidents.size() ; index ++)	{
    		Incident i = incidents.get(index) ; 
    		
    		AdminIncidentsViewModel vm = new AdminIncidentsViewModel(
    											i.getId()
    											, i.getTitle()
    											, i.getRegisteredUser().getUsername()
    											, i.getResolverUser().getUsername()
    											, i.getUrgency()
    											, i.getPriority()
    											, i.getCreatedDate()
    											, null
    											, i.getClosedDate()
    											, i.getServices().getServiceName()
    											, getIncidentStatus(i.getResolverUser(), i.getResolved(), i.getClosed())
    											, i.getDepartment().getDepartmentName()
    											, i.getEscalated()
    											) ;
    		incidentVMs.add(vm) ; 
    	}
    	
    	return incidentVMs ; 
    }
    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }
    public String getClosedDate() {
        return closedDate;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public void setLastResolveDate(String lastResolveDate) {
        this.lastResolveDate = lastResolveDate;
    }
    public String getLastResolveDate() {
        return lastResolveDate;
    }
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
    }
    public Integer getUrgency() {
        return urgency;
    }
    public String getCreatorUsername() {
        return creatorUsername;
    }
    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public Boolean getEscalation() {
        return escalation;
    }
    public void setEscalation(Boolean escalation) {
        this.escalation = escalation;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
    public String getResolverUsername() {
        return resolverUsername;
    }
    public void setResolverUsername(String resolverUsername) {
        this.resolverUsername = resolverUsername;
    }
    
}