package com.example.ppis.controllers.viewModels;

import java.util.Date;

public class AdminIncidentsViewModel{
    private Long id;
    private String title;
    private String creatorUsername;
    private String resolverUsername;
    private Integer urgency;
    private Integer priority;
    private Date createdDate;
    private String serviceName;
    private Date lastResolveDate;
    private Date closedDate;
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
        this.id = id;
        this.title = title;
        this.urgency = urgency;
        this.createdDate = createdDate;
        this.lastResolveDate = lastResolveDate;
        this.closedDate = closedDate;
        this.serviceName = serviceName;
        this.status = status;
        this.creatorUsername = creatorUsername;
        this.resolverUsername = resolverUsername;
        this.priority = priority;
        this.departmentName = departmentName;
        this.escalation = escalation;
    }

    public void setClosedDate(Date closedDate) {
        this.closedDate = closedDate;
    }
    public Date getClosedDate() {
        return closedDate;
    }
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
    public Date getCreatedDate() {
        return createdDate;
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