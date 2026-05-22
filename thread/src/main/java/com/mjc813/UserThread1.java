package com.mjc813;

public class UserThread1 extends Thread{
    private Calculator calculator;

    public UserThread1() {
        setName("UserThread2");
    }
    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }
    @Override
    public void run() {
        calculator.setMemory1(100);
    }
}
