package com.example.ppis.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.ppis.dao.DepartmentDAO;

@RestController
@RequestMapping("/departments") 
public class DepartmentController {
	
	DepartmentDAO departmentDao ; 
	
	@Autowired
	public void setDepartmentDAO(DepartmentDAO departmentDao)	{
		this.departmentDao = departmentDao ; 
	}
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public ResponseEntity getAllRequests()	{
		try {
			List<String> departments = this.departmentDao.getDepartments() ; 
			return ResponseEntity.status(HttpStatus.OK).body(departments) ; 
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()) ; 
		}
	}
}
