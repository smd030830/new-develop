package com.mjc813.test0313;

import java.util.Scanner;

public class task06 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Thread th = null;
        task06_1 obj = new task06_1();
        while(true) {
            String input = scanner.nextLine();
            if ( th != null ) {
                th.interrupt();
            }
            th = new Thread(new OutputThread(input));
            obj.start();
        }
    }
}
