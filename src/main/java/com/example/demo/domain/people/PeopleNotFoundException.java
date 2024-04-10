package com.example.demo.domain.people;

public class PeopleNotFoundException extends RuntimeException {
    public PeopleNotFoundException(String msg) {
        super(msg);
    }
}
