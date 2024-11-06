package com.example.productservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productservice")
public class ProductServiceRestController {

    @GetMapping()
    public ResponseEntity<?> index(){
        return ResponseEntity.ok("this is Product Service");
    }
}
