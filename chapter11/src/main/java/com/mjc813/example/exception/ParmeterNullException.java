package com.mjc813.example.exception;

public class ParmeterNullException extends Exception {
    private String methodName;
    public ParmeterNullException(String methodName, String msg) {
        super(msg);
        this.methodName = methodName;
    }
}
