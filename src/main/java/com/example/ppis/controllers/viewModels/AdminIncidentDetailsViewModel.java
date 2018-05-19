package com.example.ppis.controllers.viewModels;

import java.util.Date;

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
    private Date createdDate;
    private Date lastResolveDate;
    private Date closedDate;
    private String contactMethod;
    private String status;
    private Boolean escalation;

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

        this.id = id;
        this.creatorUsername = creatorUsername;
        this.resolverUsername = resolverUsername;
        this.title = title;
        this.serviceName = serviceName;
        this.departmentName = departmentName;
        this.description = description;
        this.urgency = urgency;
        this.priority = priority;
        this.createdDate = createdDate;
        this.lastResolveDate = lastResolveDate;
        this.closedDate = closedDate;
        this.contactMethod = contactMethod;
        this.status = status;
        this.escalation = escalation;
    }


    public Date getClosedDate() {
        return closedDate;
    }
    public String getContactMethod() {
        return contactMethod;
    }
    public Date getCreatedDate() {
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
    public Date getLastResolveDate() {
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
    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }
    public void setContactMethod(String contactMethod) {
        this.contactMethod = contactMethod;
    }
    public void setCreatedDate(Date createdDate) {
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
    public void setLastResolveDate(Date lastResolveDate) {
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