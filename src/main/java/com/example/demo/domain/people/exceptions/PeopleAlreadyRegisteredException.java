package com.example.demo.domain.people.exceptions;

public class PeopleAlreadyRegisteredException extends RuntimeException {
    public PeopleAlreadyRegisteredException(String msg) {
        super(msg);
    }
}
