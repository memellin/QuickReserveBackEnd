package com.example.demo.config;

import com.example.demo.domain.school.exceptions.SchoolNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionEntityHandler {
    @ExceptionHandler(SchoolNotFoundException.class)
    public ResponseEntity handleSchoolNotFound(SchoolNotFoundException e){
        return ResponseEntity.notFound().build();
    }
}
