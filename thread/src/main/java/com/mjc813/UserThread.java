package com.mjc813;

public class UserThread extends Thread{
    private Calculator calculator;

    public UserThread() {
        setName("UserThread1");
    }
    public void setCalculator(Calculator calculator) {
        this.calculator = calculator;
    }
    @Override
    public void run() {
        calculator.setMemory1(100);
    }
}
