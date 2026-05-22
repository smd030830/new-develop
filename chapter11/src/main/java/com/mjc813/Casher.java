package com.mjc813;

public class Casher {
    public void calculMoney(int ... arrs){
        Calculator calculator = new Calculator();
        double res = 0.0;
        try {
            res = calculator.devide(arrs);
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
        System.out.println("res : "+res);
    }
}
