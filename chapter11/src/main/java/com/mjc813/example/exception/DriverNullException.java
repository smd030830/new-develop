package com.mjc813.example.exception;

import lombok.Getter;

@Getter
public class DriverNullException extends Exception {
    private int errorCode;
    public DriverNullException(int errorCode, String msg) {
        super(msg);
        this.errorCode = errorCode;
    }
}
