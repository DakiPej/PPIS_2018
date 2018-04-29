package com.example.ppis.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.ppis.models.Department;

public interface DepartmentRepository extends CrudRepository<Department, Long>{
    
	public Department findDepartmentByDepartmentName(String departmentName);
	public boolean existsByDepartmentName (String departmentName) ; 
}
