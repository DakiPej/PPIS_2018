package com.example.ppis.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Incident {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@ManyToOne
	private RegisteredUser registeredUser;
	
	@ManyToOne
	private RegisteredUser resolverUser;
	
	@ManyToOne
	private RegisteredUser admin ;
	
	@NotNull
	@ManyToOne
	private ContactMethod contactMethod;
	
	@NotNull
	@ManyToOne
	private Services services;
	
	@ManyToOne
	private Department department;
	
	@NotNull
	@Size(max = 30)
	private String title;
	
	@NotNull
	@Size(max = 2000)
	private String description;
	
	private Integer priority;
	
	@NotNull
	private Integer urgency;
	
	@NotNull
	@Size(max = 50)
	private String contactInfo;
	
	@NotNull
    @Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date closedDate;
	
	@Column(columnDefinition="BIT")
	private Boolean resolved;
	
	@Column(columnDefinition="BIT")
	private Boolean escalated ; 
	
	public Incident()	{
		
	}
	
	public Incident(RegisteredUser registeredUser
			//, RegisteredUser resolverUser
			, ContactMethod contactMethod
			, Services services 
			//, Department department
			, String title
			, String description
			//, Integer priority 
			, Integer urgency
			, String contactInfo
			//, Date createDate
			//, Date closedDate)	
				)	{
		
		this.registeredUser = registeredUser;
		this.resolverUser = null; 
		this.admin = null ; 
		this.contactMethod = contactMethod; 
		this.services = services; 
		this.department = null; 
		this.title = title; 
		this.description = description; 
		this.priority = null; 
		this.urgency = urgency; 
		this.contactInfo = contactInfo; 
		
		this.createdDate = new Date(); 
		this.closedDate = null; 
		this.closed = false; 
		this.resolved = false; 
		this.escalated = false ; 
	}

	@Column(columnDefinition="BIT")
	private Boolean closed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RegisteredUser getRegisteredUser() {
		return registeredUser;
	}

	public void setRegisteredUser(RegisteredUser registeredUser) {
		this.registeredUser = registeredUser;
	}

	public RegisteredUser getResolverUser() {
		return resolverUser;
	}

	public void setResolverUser(RegisteredUser resolverUser) {
		this.resolverUser = resolverUser;
	}

	public ContactMethod getContactMethod() {
		return contactMethod;
	}

	public void setContactMethod(ContactMethod contactMethod) {
		this.contactMethod = contactMethod;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public Integer getUrgency() {
		return urgency;
	}

	public void setUrgency(Integer urgency) {
		this.urgency = urgency;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(Date closedDate) {
		this.closedDate = closedDate;
	}

	public Boolean getClosed() {
		return closed;
	}

	public void setClosed(Boolean closed) {
		this.closed = closed;
	}

	public Services getServices() {
		return services;
	}

	public void setServices(Services services) {
		this.services = services;
	}
	public Boolean getEscalated()	{
		return escalated ; 
	}
	public void setEscalated(Boolean escalated)	{
		this.escalated = escalated ; 
	}
	
	public Boolean getResolved() {
		return resolved;
	}

	public void setResolved(Boolean resolved) {
		this.resolved = resolved;
	}
	public RegisteredUser getAdmin() {
		return admin ; 
	}
	public void setAdmin(RegisteredUser admin)	{
		this.admin = admin ;
	}
}
