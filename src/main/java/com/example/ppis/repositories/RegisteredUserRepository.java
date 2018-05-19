package com.example.ppis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.ppis.models.Department;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.models.UserType;

public interface RegisteredUserRepository extends CrudRepository<RegisteredUser, Long>{
	
	public boolean existsByUsername(String username) ; 
	
	@Query("SELECT u.email FROM RegisteredUser u WHERE u.username = :username AND u.password = :password")
	String findUser(@Param("username") String username, @Param("password") String password);
	
	@Query("SELECT u FROM RegisteredUser u WHERE u.username = :username")
	RegisteredUser findByUsername(@Param("username") String username);
	
	RegisteredUser findDistinctRegisteredUserByUsername(String username);
	
	public List<RegisteredUser> findAllByUserType(UserType userType) ; 
	
	@Query("SELECT ru FROM RegisteredUser ru, UserType ut where ru.userType =:userType AND ut.department =:department")
	public List<RegisteredUser> getUsersByDepartment(@Param("userType") UserType userType, @Param("department") Department department) ;
	
	@Query("SELECT d FROM RegisteredUser ru, UserType ut, Department d where ru.username =:username AND ru.userType =:userType AND ut.department = d.id")
	public Department getUserDepartment(@Param("username") String username, @Param("userType") UserType userType) ; 
}
