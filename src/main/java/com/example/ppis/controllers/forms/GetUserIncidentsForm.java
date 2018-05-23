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
}