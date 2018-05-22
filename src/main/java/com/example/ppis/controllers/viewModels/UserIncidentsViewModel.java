package com.example.ppis.controllers.viewModels;

import java.util.Date;

public class UserIncidentsViewModel{
    private Long id;
    private String title;
    private Date createdDate;
    private Date lastResolveDate;
    private Date closedDate;
    private String serviceName;
    private String status;

    public UserIncidentsViewModel(Long id, 
                                String title, 
                                Date createdDate, 
                                Date lastResolveDate, 
                                Date closedDate, 
                                String serviceName, 
                                String status){

        this.id = id;
        this.title = title;
        this.createdDate = createdDate;
        this.lastResolveDate = lastResolveDate;
        this.closedDate = closedDate;
        this.serviceName = serviceName;
        this.status = status;
    }

    public Date getClosedDate() {
        return closedDate;
    }
    
    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }
    
    public Date getCreatedDate() {
        return createdDate;
    }
    
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Date getLastResolveDate() {
        return lastResolveDate;
    }
    
    public void setLastResolveDate(Date lastResolveDate) {
        this.lastResolveDate = lastResolveDate;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
}
