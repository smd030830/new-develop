package com.mjc813;

public class Calculator {

    public long add(int ... arrs) {
        long result = 0L;
        for (int i = 0; i < arrs.length; i++) {
            result += arrs[i];
        }
        return result;
    }
    public double devide(int ... arrs) throws ArrayIndexOutOfBoundsException {
        double result = 0L;
        for (int i = 1; i < arrs.length; i++) {
            result /= arrs[i];
        }
        return result;
    }
}
