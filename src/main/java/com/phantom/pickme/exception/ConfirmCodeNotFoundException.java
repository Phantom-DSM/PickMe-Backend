package com.phantom.pickme.exception;

public class ConfirmCodeNotFoundException extends RuntimeException {
    public ConfirmCodeNotFoundException(String msg) {
        super(msg);
    }
}
