package com.example.ppis.controllers;

import com.example.ppis.controllers.forms.RequestMessageForm;
import com.example.ppis.services.RequestMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;

@CrossOrigin
@RestController
@RequestMapping("/requestMessages")
public class RequestMessageController {

    @Autowired
    RequestMessageService requestMessageService;

    @RequestMapping(value = "/sendFromUserToResolver", method = RequestMethod.POST)
    public ResponseEntity<?> sendFromUserToResolver(@RequestBody RequestMessageForm requestMessageForm) {
        try {
            return ResponseEntity.ok(requestMessageService.create(requestMessageForm, "KORISNIK", "ODJEL"));
        }
        catch (ServletException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()) ;
        }
    }

    @RequestMapping(value = "/sendFromResolverToUser", method = RequestMethod.POST)
    public ResponseEntity<?> sendFromResolverToUser(@RequestBody RequestMessageForm requestMessageForm) {
        try {
            return ResponseEntity.ok(requestMessageService.create(requestMessageForm, "ODJEL", "KORISNIK"));
        }
        catch (ServletException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()) ;
        }
    }

    @RequestMapping(value = "/sendFromResolverToAdmin", method = RequestMethod.POST)
    public ResponseEntity<?> sendFromResolverToAdmin(@RequestBody RequestMessageForm requestMessageForm) {
        try {
            return ResponseEntity.ok(requestMessageService.create(requestMessageForm, "ODJEL", "ADMIN"));
        }
        catch (ServletException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()) ;
        }
    }

    @RequestMapping(value = "/sendFromAdminToResolver", method = RequestMethod.POST)
    public ResponseEntity<?> sendFromAdminToResolver(@RequestBody RequestMessageForm requestMessageForm) {
        try {
            return ResponseEntity.ok(requestMessageService.create(requestMessageForm, "ADMIN", "ODJEL"));
        }
        catch (ServletException ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()) ;
        }
    }

    @RequestMapping(value = "/getSentMessages/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getSentMessages(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(requestMessageService.getRequestMessagesBySenderId(id));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()) ;
        }
    }

    @RequestMapping(value = "/getReceivedMessages/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getReceivedMessages(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(requestMessageService.getRequestMessagesByReceiverId(id));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()) ;
        }
    }
 }
