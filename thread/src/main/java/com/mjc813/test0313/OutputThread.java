package com.mjc813.test0313;

public class OutputThread extends Thread {
    private String ch1;

    public OutputThread(String ch1) {
        this.ch1 = ch1;
    }

    @Override
    public void run() {
        while (true) {
            try {
                for (int i = 1; i < 10; i++) {
                    System.out.println(i + "단");
                    for (int j = 1; j < 10; j++) {
                        System.out.println(i + " * " + j + " = " + (i * j));
                        Thread.sleep(300);
                    } catch(Exception e){
                        System.out.println("종료");
                        break;
                    }
                }
            }
        }