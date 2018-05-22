package com.example.ppis.controllers.forms;

public class EscalationForm{
    private Long id;
    private Boolean escalation;
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }
    public Boolean getEscalation() {
        return escalation;
    }
    public Long getId() {
        return id;
    }

}