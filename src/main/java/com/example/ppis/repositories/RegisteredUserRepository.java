package com.example.ppis.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.ppis.models.RegisteredUser;

public interface RegisteredUserRepository extends CrudRepository<RegisteredUser, Long>{
	
	@Query("SELECT u.email FROM RegisteredUser u WHERE u.username = :username AND u.password = :password")
	String findUser(@Param("username") String username, @Param("password") String password);
	
	@Query("SELECT u FROM RegisteredUser u WHERE u.username = :username")
	RegisteredUser findByUsername(@Param("username") String username);
}
