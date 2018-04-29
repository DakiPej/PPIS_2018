package com.example.ppis.dao;

import org.springframework.stereotype.Repository;

import com.example.ppis.models.Department;
import com.example.ppis.repositories.DepartmentRepository;

@Repository
public class DepartmentDAO extends BaseDAO<Department, DepartmentRepository>{
    
	public Department getDepartmentByName(String departmentName) {
        return this.repo.findDepartmentByDepartmentName(departmentName);
    }
	
	public boolean existsByDepartmentName(String departmentName)	{
		return this.repo.existsByDepartmentName(departmentName) ; 
	}
}
