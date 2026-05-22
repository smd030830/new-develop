package com.mjc813.test0313;

public class task02 {


    public static class thread1 extends Thread {
        public void run() {
            try {
                for (int i = 2; i < 4; i++) {
                    System.out.println(i + "단");
                    for (int j = 1; j < 10; j++) {
                        System.out.println(i + " * " + j + " = " + (i * j));
                        sleep(50);
                    }
                }
            } catch (Exception e) {
            }
        }
    }
    public static class thread2 extends Thread {
        public void run() {
            try {
                for (int i = 4; i < 7; i++) {
                    System.out.println(i + "단");
                    for (int j = 1; j < 10; j++) {
                        System.out.println(i + " * " + j + " = " + (i * j));
                        sleep(50);
                    }
                }
            } catch (Exception e) {
            }
        }
    }
}
