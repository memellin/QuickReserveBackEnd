package com.example.demo.domain.checkin.exceptions;

public class CheckedInAlreadyExistsExceptions extends RuntimeException{

    public CheckedInAlreadyExistsExceptions(String msg) {
        super(msg);
    }
}
