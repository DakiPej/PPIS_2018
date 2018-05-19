package com.example.ppis.controllers.viewModels;

import java.util.Date;

public class DepartmentDetailsViewModel{
    private Long id;
    private String adminUsername;
    private String title;
    private String serviceName;
    private String description;
    private Integer priority;
    private Date createdDate;
    private Date lastResolveDate;
    private Date closedDate;
    private String status;
    private Boolean escalation;

    public DepartmentDetailsViewModel(Long id,
                                String adminUsername,
                                String title,
                                String serviceName,
                                String description,
                                Integer priority,
                                Date createdDate,
                                Date lastResolveDate,
                                Date closedDate,
                                String status,
                                Boolean escalation){

        this.id = id;
        this.title = title;
        this.serviceName = serviceName;
        this.description = description;
        this.priority = priority;
        this.createdDate = createdDate;
        this.lastResolveDate = lastResolveDate;
        this.closedDate = closedDate;
        this.status = status;
        this.escalation = escalation;
    }
}