package com.example.userservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userservice")
public class UserServiceRestController {

    @GetMapping()
    public ResponseEntity<?> index() {
        return ResponseEntity.ok("this is User Service");
    }
}
