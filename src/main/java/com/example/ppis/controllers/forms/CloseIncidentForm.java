package com.example.ppis.controllers.forms;

public class CloseIncidentForm{
    private String username;
    private Long id;
    private Boolean close;

    public Long getId() {
        return id;
    }
    
    public String getUsername() {
        return username;
    }

    public Boolean getClose() {
        return close;
    }
}