package com.example.ppis.controllers.viewModels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.example.ppis.models.Incident;
import com.example.ppis.models.RegisteredUser;

public class UserIncidentDetailsViewModel{
    private Long id;
    private String title;
    private String description;
    private Integer urgency;
    private String contactMethod;
    private String createdDate;
    private String lastResolveDate;
    private String closedDate;
    private String serviceName;
    private String status;
    
    public UserIncidentDetailsViewModel()	{
    	
    }
    
    public UserIncidentDetailsViewModel(Long id,
                                        String title,
                                        String description,
                                        Integer urgency,
                                        String contactMethod,
                                        Date createdDate,
                                        Date lastResolveDate,
                                        Date closedDate,
                                        String serviceName,
                                        String status){
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss") ; 
    	
        this.id = id;
        this.title = title;
        this.description = description;
        this.urgency = urgency;
        this.contactMethod = contactMethod;
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
    
    public UserIncidentDetailsViewModel convertToVM(Incident i)	{
    	
    	UserIncidentDetailsViewModel vm = new UserIncidentDetailsViewModel(
    											i.getId()
    											, i.getTitle()
    											, i.getDescription()
    											, i.getUrgency()
    											, i.getContactMethod().getContactMethodName()
    											, i.getCreatedDate()
    											, i.getLastResolvedDate()
    											, i.getClosedDate()
    											, i.getServices().getServiceName()
    											, getIncidentStatus(i.getResolverUser(), i.getResolved(), i.getClosed())
    											) ;  
    	return vm ; 
    }
    
    public void setClosedDate(String closedDate) {
        this.closedDate = closedDate;
    }
    public String getClosedDate() {
        return closedDate;
    }
    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }
    public String getContactMethod() {
        return contactMethod;
    }
    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }
    public String getCreatedDate() {
        return createdDate;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
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
    
}