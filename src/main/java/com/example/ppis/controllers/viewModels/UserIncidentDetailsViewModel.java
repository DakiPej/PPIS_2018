package com.example.ppis.controllers.viewModels;

import java.util.Date;

public class UserIncidentDetailsViewModel{
    private Long id;
    private String title;
    private String description;
    private Integer urgency;
    private String contactMethod;
    private Date createdDate;
    private Date lastResolveDate;
    private Date closedDate;
    private String serviceName;
    private String status;
    
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
        this.id = id;
        this.title = title;
        this.description = description;
        this.urgency = urgency;
        this.contactMethod = contactMethod;
        this.createdDate = createdDate;
        this.lastResolveDate = lastResolveDate;
        this.closedDate = closedDate;
        this.serviceName = serviceName;
        this.status = status;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }
    public Date getClosedDate() {
        return closedDate;
    }
    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }
    public String getContactMethod() {
        return contactMethod;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Date getCreatedDate() {
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
    public void setLastResolveDate(Date lastResolveDate) {
        this.lastResolveDate = lastResolveDate;
    }
    public Date getLastResolveDate() {
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