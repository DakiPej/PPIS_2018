package com.example.ppis.controllers;

import com.example.ppis.controllers.forms.MessageForm;
import com.example.ppis.services.RequestMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/requestMessages")
public class RequestMessageController {

    @Autowired
    RequestMessageService requestMessageService;

//    @RequestMapping(value = "/sendFromUserToResolver", method = RequestMethod.POST)
//    public ResponseEntity<?> sendFromUserToResolver(@RequestBody MessageForm requestMessageForm) {
//        try {
//            return ResponseEntity.ok(requestMessageService.create(requestMessageForm, "KORISNIK", "ODJEL"));
//        }
//        catch (ServletException ex) {
//            System.out.println(ex.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()) ;
//        }
//    }
//
//    @RequestMapping(value = "/sendFromResolverToUser", method = RequestMethod.POST)
//    public ResponseEntity<?> sendFromResolverToUser(@RequestBody MessageForm requestMessageForm) {
//        try {
//            return ResponseEntity.ok(requestMessageService.create(requestMessageForm, "ODJEL", "KORISNIK"));
//        }
//        catch (ServletException ex) {
//            System.out.println(ex.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()) ;
//        }
//    }
//
//    @RequestMapping(value = "/sendFromResolverToAdmin", method = RequestMethod.POST)
//    public ResponseEntity<?> sendFromResolverToAdmin(@RequestBody MessageForm requestMessageForm) {
//        try {
//            return ResponseEntity.ok(requestMessageService.create(requestMessageForm, "ODJEL", "ADMIN"));
//        }
//        catch (ServletException ex) {
//            System.out.println(ex.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()) ;
//        }
//    }
//
//    @RequestMapping(value = "/sendFromAdminToResolver", method = RequestMethod.POST)
//    public ResponseEntity<?> sendFromAdminToResolver(@RequestBody MessageForm requestMessageForm) {
//        try {
//            return ResponseEntity.ok(requestMessageService.create(requestMessageForm, "ADMIN", "ODJEL"));
//        }
//        catch (ServletException ex) {
//            System.out.println(ex.getMessage());
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()) ;
//        }
//    }

    @RequestMapping(value = "/getSentMessages", method = RequestMethod.GET)
    public ResponseEntity<?> getSentMessages(@RequestParam(name = "userId") Long id) {
        try {
            return ResponseEntity.ok(requestMessageService.getRequestMessagesBySenderId(id));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()) ;
        }
    }

    @RequestMapping(value = "/getReceivedMessages", method = RequestMethod.GET)
    public ResponseEntity<?> getReceivedMessages(@RequestParam(name = "userId") Long id) {
        try {
            return ResponseEntity.ok(requestMessageService.getRequestMessagesByReceiverId(id));
        }
        catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage()) ;
        }
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseEntity<?> sendMessage(@RequestBody MessageForm messageForm) {
        try {
            return ResponseEntity.ok(requestMessageService.create(messageForm));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(name = "userId") Long id) {
        try {
            return ResponseEntity.ok(requestMessageService.getAllRequestMessagesByUserId(id));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
 }
