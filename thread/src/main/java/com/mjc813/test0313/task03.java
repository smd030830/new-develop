package com.mjc813.test0313;

public class task03 {
    public static class thread3 extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 7; i < 10; i++) {
                    System.out.println(i + "단");
                    for (int j = 1; j < 10; j++) {
                        System.out.println(i + " * " + j + " = " + (i * j));
                        sleep(50);
                    }
                }
            }catch (InterruptedException e) {}
        }
    }
}
