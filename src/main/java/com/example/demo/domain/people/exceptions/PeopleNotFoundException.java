package com.example.demo.domain.people.exceptions;

public class PeopleNotFoundException extends RuntimeException {
    public PeopleNotFoundException(String msg) {
        super(msg);
    }
}
