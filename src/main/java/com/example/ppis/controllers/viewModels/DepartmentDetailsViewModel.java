package com.example.ppis.controllers.viewModels;

import java.util.Date;

public class DepartmentDetailsViewModel{
    private Long id;
    private String creatorUsername;
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
    	this.creatorUsername=adminUsername;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreatorUsername() {
		return creatorUsername;
	}

	public void setCreatorUsername(String adminUsername) {
		this.creatorUsername = adminUsername;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getLastResolveDate() {
		return lastResolveDate;
	}

	public void setLastResolveDate(Date lastResolveDate) {
		this.lastResolveDate = lastResolveDate;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getEscalation() {
		return escalation;
	}

	public void setEscalation(Boolean escalation) {
		this.escalation = escalation;
	}
}