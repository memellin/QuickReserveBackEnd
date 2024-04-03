package com.example.demo.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/peoples")
public class PeopleController {
    @GetMapping
    public ResponseEntity<String> getTest(){
        return ResponseEntity.ok("success");
    }
}
