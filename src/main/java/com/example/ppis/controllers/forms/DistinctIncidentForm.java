package com.example.ppis.controllers.forms;

import javax.validation.constraints.NotNull;

public class DistinctIncidentForm{
    @NotNull
    private Long id;
    @NotNull
    private String username;

    public Long getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}