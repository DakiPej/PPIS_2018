package com.example.ppis.controllers;

import com.example.ppis.controllers.forms.MessageForm;
import com.example.ppis.services.IncidentMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/incidentMessages")
public class IncidentMessageController {

    @Autowired
    IncidentMessageService incidentMessageService;

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public ResponseEntity<?> sendMessage(@RequestBody MessageForm messageForm) {
        try {
            return ResponseEntity.ok(incidentMessageService.create(messageForm));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ResponseEntity<?> getAll(@RequestParam(name = "userId") Long id) {
        try {
            return ResponseEntity.ok(incidentMessageService.getAllIncidentMessagesByUserId(id));
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }
}
