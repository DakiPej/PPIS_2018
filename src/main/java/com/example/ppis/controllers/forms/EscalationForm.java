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
    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
    public void setEscalation(Boolean escalation) {
        this.escalation = escalation;
    }
    public void setId(Long id) {
        this.id = id;
    }

}