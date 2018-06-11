package com.phantom.pickme.exception;

public class UserCodeNotFoundException extends RuntimeException {
    public UserCodeNotFoundException(String msg) {
        super(msg);
    }
}
