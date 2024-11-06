package com.example.orderservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orderservice")
public class OrderServiceRestController {

    @GetMapping
    public ResponseEntity<?> index(){
        return ResponseEntity.ok("this is Order Service");
    }
}
