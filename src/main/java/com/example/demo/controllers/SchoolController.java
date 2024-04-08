package com.example.demo.controllers;

import com.example.demo.services.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService service;

    @GetMapping("/{schoolId}")
    public ResponseEntity<String> getSchool(@PathVariable String schoolId){ // qnd apertar no botao de entrar na escola, vem nesse código aqui pegando o id da escola

        return ResponseEntity.ok("sucesso!");
    }
}
