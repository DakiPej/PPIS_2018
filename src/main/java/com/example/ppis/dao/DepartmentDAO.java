package com.example.ppis.dao;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<String> getDepartments()	{
		List<Department> departments = (List<Department>)this.repo.findAll() ;
		List<String> departmentNames = new ArrayList<>() ; 
		
		for(int i = 0 ; i <departments.size() ; i++)	
			departmentNames.add(departments.get(i).getDepartmentName()) ; 
		return departmentNames ; 
		
		
	}
}
