package com.mjc813;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();

        UserThread1 userThread1 = new UserThread1();
        userThread1.setCalculator(calculator);
        userThread1.start();



    }
}