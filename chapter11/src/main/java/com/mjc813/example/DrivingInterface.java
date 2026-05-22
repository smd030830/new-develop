package com.mjc813.example;

import com.mjc813.example.exception.AvanteNullException;
import com.mjc813.example.exception.DriverNullException;
import com.mjc813.example.exception.ParmeterNullException;

public interface DrivingInterface {
    void doSomething(Integer pedal) throws ParmeterNullException, AvanteNullException, DriverNullException;
}

