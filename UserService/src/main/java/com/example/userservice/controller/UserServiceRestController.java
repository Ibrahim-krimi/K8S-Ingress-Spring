package com.example.userservice.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/userservice" ,produces = MediaType.APPLICATION_JSON_VALUE)
public class UserServiceRestController {

    @GetMapping()
    public ResponseEntity<?> index() {

        Map<String, String> response = new HashMap<>();
        response.put("message", "this is User Service");

        return ResponseEntity.ok(response);
    }
}
