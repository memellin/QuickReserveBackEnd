package com.example.demo.domain.school.exceptions;

public class SchoolNotFoundException extends RuntimeException {

    public SchoolNotFoundException(String msg) {
        super(msg);
    }

}
