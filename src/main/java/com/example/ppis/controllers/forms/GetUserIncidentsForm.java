package com.example.ppis.controllers.forms;

import javax.validation.constraints.NotNull;

public class GetUserIncidentsForm{
    @NotNull 
    private String username;
    private String filter;
    private String departmentName;
    private String escalated;

    public String getDepartmentName() {
        return departmentName;
    }
    public String getEscalated() {
        return escalated;
    }
    public String getFilter() {
        return filter;
    }
    public String getUsername() {
        return username;
    }
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public void setEscalated(String escalated) {
        this.escalated = escalated;
    }
    public void setFilter(String filter) {
        this.filter = filter;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}