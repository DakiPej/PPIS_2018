package com.example.ppis.controllers.forms;

public class AssignIncidentForm{
    private String adminUsername;
    private String departmentName;
    private Long id;
    private Integer priority;
    
    public String getAdminUsername() {
        return adminUsername;
    }
    
    public String getDepartmentName() {
        return departmentName;
    }
    
    public Long getId() {
        return id;
    }
    
    public Integer getPriority() {
        return priority;
    }
}