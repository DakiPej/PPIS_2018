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
    public void setClose(Boolean close) {
        this.close = close;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }

}