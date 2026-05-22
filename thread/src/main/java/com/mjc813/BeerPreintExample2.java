package com.mjc813;

import java.awt.*;

public class BeerPreintExample2 {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                Toolkit toolkit = Toolkit.getDefaultToolkit();
                for(int i = 0; i < 5; i++) {
                    toolkit.beep();
                    try {
                        Thread.sleep(500);
                    } catch (Exception e) {}
                }
            }
        });
        thread1.start();
        for(int i = 0; i < 5; i++) {
            System.out.println("띵");
            try {
                Thread.sleep(500);
            }catch (Exception e) {}
        }
    }
}
