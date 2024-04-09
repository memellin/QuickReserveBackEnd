package com.example.demo.controllers;

import com.example.demo.dto.people.PeopleListResponseDTO;
import com.example.demo.dto.school.SchoolIdDTO;
import com.example.demo.dto.school.SchoolRequestDTO;
import com.example.demo.dto.school.SchoolResponseDTO;
import com.example.demo.services.PeopleService;
import com.example.demo.services.SchoolService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/schools")
@RequiredArgsConstructor
public class SchoolController {

    private final SchoolService schoolService;
    private final PeopleService peopleService;

    @GetMapping("/{schoolId}")
    public ResponseEntity<SchoolResponseDTO> getSchool(@PathVariable String schoolId){ // qnd apertar no botao de entrar na escola, vem nesse código aqui pegando o id da escola
        SchoolResponseDTO school = this.schoolService.getSchoolDetail(schoolId);
        return ResponseEntity.ok(school);
    }

    @PostMapping
    public ResponseEntity<SchoolIdDTO> createSchool(@RequestBody SchoolRequestDTO body, UriComponentsBuilder uriComponentsBuilder){
        SchoolIdDTO schoolIdDTO =  this.schoolService.createSchool(body);

        var uri = uriComponentsBuilder.path("/schools/{schoolId}").buildAndExpand(schoolIdDTO.schoolId()).toUri();

        return ResponseEntity.created(uri).body(schoolIdDTO);
    }

    @GetMapping("/peoples/{id}")
    public ResponseEntity<PeopleListResponseDTO> getSchoolPeople(@PathVariable String id){
        PeopleListResponseDTO peopleListResponseDTO = this.peopleService.getSchoolsPeople(id);
        return ResponseEntity.ok(peopleListResponseDTO);
    }
}