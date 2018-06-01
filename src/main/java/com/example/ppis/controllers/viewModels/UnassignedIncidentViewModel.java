package com.example.ppis.controllers.viewModels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.ppis.models.Incident;

public class UnassignedIncidentViewModel{
    private Long id;
    private String title;
    private String creator;
    private Integer priority;
    private String createdDate;
    private String serviceName;
    private String departmentName;
    
    public UnassignedIncidentViewModel()	{
    	
    }
    
    public UnassignedIncidentViewModel(Long id
    		, String title
    		, String creator
    		, Integer priority
    		, Date createdDate
    		, String serviceName
    		, String departmentName)	{
    	
    	DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss") ;
    	
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.priority = priority;

        if (createdDate != null) this.createdDate = df.format(createdDate);
        else this.createdDate = "";
        //this.createdDate =  df.format(createdDate);
        
        this.serviceName = serviceName;
        this.departmentName = departmentName;        
    }

    public List<UnassignedIncidentViewModel> convertToVMs(List<Incident> incidents)	{
    	List<UnassignedIncidentViewModel> vms = new ArrayList<>() ; 
    	
    	for(int index = 0 ; index < incidents.size() ; index++ ) {
    		Incident i = incidents.get(index) ; 
    		
    		UnassignedIncidentViewModel vm = new UnassignedIncidentViewModel(
    												i.getId()
    												, i.getTitle()
    												, i.getRegisteredUser().getUsername()
    												, i.getPriority()
    												, i.getCreatedDate()
    												, i.getServices().getServiceName()
    												, i.getDepartment().getDepartmentName()
    												) ;
    		
    		vms.add(vm) ; 
    	}
    	
    	return vms ; 
    }
    
    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getCreator() {
        return creator;
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
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    public String getServiceName() {
        return serviceName;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    public String getDepartmentName() {
        return departmentName;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public Integer getPriority() {
        return priority;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}