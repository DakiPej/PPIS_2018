package com.example.ppis.controllers.viewModels;

import java.util.Date;

public class UnassignedIncidentViewModel{
    private Long id;
    private String title;
    private String creator;
    private Integer priority;
    private Date createdDate;
    private String serviceName;
    private String departmentName;
    
    public UnassignedIncidentViewModel(Long id, String title, String creator, Integer priority, Date createdDate, String serviceName, String departmentName){
        this.id = id;
        this.title=title;
        this.creator=creator;
        this.priority=priority;
        this.createdDate=createdDate;
        this.serviceName=serviceName;
        this.departmentName=departmentName;        
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    public String getCreator() {
        return creator;
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