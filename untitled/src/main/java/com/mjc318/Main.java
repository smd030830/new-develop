package com.mjc318;

import com.mjc318.lamda.LamdaUseInterface;
import com.mjc318.lamda.NomalUseInterface;

public class Main {
    public static void main(String[] args) {
        NomalUseInterface nui = new NomalUseInterface();
        nui.dosome();

        LamdaUseInterface lui = new LamdaUseInterface();
        lui.dosome((str, n) -> {
            System.out.println(str.length() == n ? "같다" : "다르다");
        },"abc",3);

        lui.dosome( (sss, i) -> {
            for ( int m = 0; m < i; m++ ) {
                System.out.print(sss);
            }
            System.out.println();
        }, "def", 3);
    }
}
