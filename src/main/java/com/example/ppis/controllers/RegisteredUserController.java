package com.example.ppis.controllers;

import javax.servlet.ServletException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ppis.controllers.forms.LoginUserForm;
import com.example.ppis.models.RegisteredUser;
import com.example.ppis.repositories.RegisteredUserRepository;
import com.example.ppis.services.RegisteredUserService;
import org.springframework.web.bind.annotation.RequestParam;


@CrossOrigin
@RestController
public class RegisteredUserController {

	@Autowired
	RegisteredUserService registeredUserService;

	@RequestMapping(value="/registration", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity registration() {
		try {
			
			return ResponseEntity.ok("");
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return ResponseEntity.badRequest().body("");
		}
	}
	
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity login(@RequestBody final LoginUserForm loginUser) throws ServletException {
		String role = registeredUserService.login(loginUser);
		if (role != null) {
			return ResponseEntity.ok(role);
		}
		else
			return ResponseEntity.badRequest().body("Korisnicko ime ili lozinka nisu ispravni.");
	}

	@RequestMapping(value="/userDetails", method=RequestMethod.POST)
	public ResponseEntity<?> requestMethodName(@RequestBody String username) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(registeredUserService.getUserDetails(username));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Korisnik ne postoji");
		}
	}
	
}
