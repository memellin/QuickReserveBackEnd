package com.example.demo.domain.school.exceptions;

public class SchoolFullException extends RuntimeException {

    public SchoolFullException(String msg) {
        super(msg);
    }
}
