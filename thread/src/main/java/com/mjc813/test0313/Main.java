package com.mjc813.test0313;

public class Main {
    public static void main(String[] args) {
        task01 task01 = new task01();
//        task01.start();

        Main.thread1 thread1 = new Main.thread1();
        task02.thread2 thread2 = new task02.thread2();
        thread1.start();
        thread2.start();

        task03 task03 = new task03();
        task03.thread3 thread3 = new task03.thread3();
        thread3.start();
    }

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
}
