package com.mjc813;

public class Calculator {
    private int memory;

    public int getMemory() {
        return memory;
    }
    public synchronized void setMemory1(int memory) {
        this.memory = memory;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName());
        }
    }
    public void setMemory2(int memory) {
        this.memory = memory;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
