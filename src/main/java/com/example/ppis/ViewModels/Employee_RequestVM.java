package com.example.ppis.ViewModels;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.ppis.models.Request;

public class Employee_RequestVM {

	private long id;
	private String creatorUsername;
	private String resolverUsername;
	// private String admin ;
	private String title;
	private String description;
	private int urgency;
	private String contactMethod;
	private String contactInfo;
	private String status;
	private String createdDate;
	private String closedDate;
	private String departmentName;

	public Employee_RequestVM() {

	}

	public Employee_RequestVM(long requestId, String creatorUser, String resolver
	// , String admin
			, String title, String description, int urgency, String contactMethod, String contactInfo, String status,
			String creationDate, String closedDate, String departmentName) {

		this.id = requestId;
		this.creatorUsername = creatorUser;
		this.resolverUsername = resolver;
		// this.admin = admin ;
		this.urgency = urgency;
		this.contactMethod = contactMethod;
		this.contactInfo = contactInfo;
		this.status = status;
		this.createdDate = creationDate;
		this.closedDate = closedDate;
		this.departmentName = departmentName;		
		this.title = title;
		this.description = description;

	}

	public List<Employee_RequestVM> convertToVM(List<Request> requests) {

		List<Employee_RequestVM> requestVMs = new ArrayList<Employee_RequestVM>();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

		for (int i = 0; i < requests.size(); i++) {
			String status;
			String closedDate;
			String resolver;
			String admin;

			if (requests.get(i).getAdmin() == null)
				admin = "The request has not been picked by any administrator yet.";
			else
				admin = requests.get(i).getAdmin().getUsername();

			if (requests.get(i).getResolverUser() == null) {
				status = "nedodijeljen";
				resolver = "The request has not been picked by any resolver yet.";
			} else {
				status = "u obradi";
				resolver = requests.get(i).getResolverUser().getUsername();
			}

			if (requests.get(i).getClosed())
				status = "zatvoren";

			if (requests.get(i).getClosedDate() != null)
				closedDate = df.format(requests.get(i).getClosedDate());
			else
				closedDate = "Not closed";

				String departmentName = "" ;
			if(requests.get(i).getDepartment() != null)	
				departmentName = requests.get(i).getDepartment().getDepartmentName() ;
			else departmentName = "Nije dodjeljen." ;
			Employee_RequestVM element = new Employee_RequestVM(requests.get(i).getId(),
					requests.get(i).getRegisteredUser().getUsername(), resolver
					// , admin
					, requests.get(i).getTitle(), requests.get(i).getDescription(), requests.get(i).getUrgency(),
					requests.get(i).getContactMethod().getContactMethodName(), requests.get(i).getContactInfo(), status,
					df.format(requests.get(i).getCreatedDate()), closedDate,
					departmentName);


			requestVMs.add(element);

		}

		return requestVMs;

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getCreatorUsername() {
		return creatorUsername;
	}

	public void setCreatorUsername(String creatorUsername) {
		this.creatorUsername = creatorUsername;
	}

	public String getResolverUsername() {
		return resolverUsername;
	}

	public void setResolverUsername(String resolverUsername) {
		this.resolverUsername = resolverUsername;
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

	public int getUrgency() {
		return urgency;
	}

	public void setUrgency(int urgency) {
		this.urgency = urgency;
	}

	public String getContactMethod() {
		return contactMethod;
	}

	public void setContactMethod(String contactMethod) {
		this.contactMethod = contactMethod;
	}

	public String getContactInfo() {
		return contactInfo;
	}

	public void setContactInfo(String contactInfo) {
		this.contactInfo = contactInfo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(String closedDate) {
		this.closedDate = closedDate;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
}
