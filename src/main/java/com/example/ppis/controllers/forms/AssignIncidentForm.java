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
    public void setAdminUsername(String adminUsername) {
        this.adminUsername = adminUsername;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setPriority(Integer priority) {
        this.priority = priority;
    }

}