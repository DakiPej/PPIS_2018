package com.example.ppis.controllers.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * CreateIncidentForm
 */
public class CreateIncidentForm {
    
    @NotNull
    @Size(min=3, max=20)
    private String username;
    
    @NotNull
    @Size(max = 20)
    private String contactMethodName;

    @NotNull
    @Size(max = 30)
    private String serviceName;

    @NotNull
    @Size(max = 20)
    private String title;

    @NotNull
    @Size(max = 2000)
    private String description;

    @NotNull
    private Integer urgency;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContactMethodName() {
        return contactMethodName;
    }

    public void setContactMethodName(String contactMethodName) {
        this.contactMethodName = contactMethodName;
    }

    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getServiceName() {
        return serviceName;
    }
    
    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public Integer getUrgency() {
        return urgency;
    }
    
    public void setUrgency(Integer urgency) {
        this.urgency = urgency;
    }

}