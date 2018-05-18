package com.example.ppis.controllers.viewModels;

import java.util.Date;

public class DepartmentIncidentsViewModel{
    
    private Long id;
    private String title;
    private Integer priority;
    private Date createdDate;
    private String serviceName;
    private Date lastResolveDate;
    private Date closedDate;
    private String status;
    private Boolean escalation;
    
    public DepartmentIncidentsViewModel(Long id,
                                        String title,
                                        Integer priority,
                                        Date createdDate,
                                        Date lastResolveDate,
                                        Date closedDate,
                                        String serviceName,
                                        String status,
                                        Boolean escalation){
        this.id = id;
        this.title = title;
        this.createdDate = createdDate;
        this.lastResolveDate = lastResolveDate;
        this.closedDate = closedDate;
        this.serviceName = serviceName;
        this.status = status;
        this.priority = priority;
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
}