package com.example.demo.controllers;

import com.example.demo.dto.school.SchoolIdDTO;
import com.example.demo.dto.school.SchoolRequestDTO;
import com.example.demo.dto.school.SchoolResponseDTO;
import com.example.demo.services.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService service;

    @GetMapping("/{schoolId}")
    public ResponseEntity<SchoolResponseDTO> getSchool(@PathVariable String schoolId){ // qnd apertar no botao de entrar na escola, vem nesse código aqui pegando o id da escola
        SchoolResponseDTO school = this.service.getSchoolDetail(schoolId);
        return ResponseEntity.ok(school);
    }

    @PostMapping
    public ResponseEntity<SchoolIdDTO> createSchool(@RequestBody SchoolRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        SchoolIdDTO schoolIdDTO =  this.service.createSchool(body);

        var uri = uriComponentsBuilder.path("/schools/{schoolId}").buildAndExpand(schoolIdDTO.schoolId()).toUri();

        return ResponseEntity.created(uri).body(schoolIdDTO);
    }
}
