package com.example.orderservice.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/orderservice", produces = MediaType.APPLICATION_JSON_VALUE)
public class OrderServiceRestController {

    @GetMapping
    public ResponseEntity<Map<String, String>> index(){
        Map<String, String> response = new HashMap<>();
        response.put("message", "this is Order Service");

        return ResponseEntity.ok(response);
    }
}
