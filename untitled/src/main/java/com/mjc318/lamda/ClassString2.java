package com.mjc318.lamda;

public class ClassString2 implements MyString{
    @Override
    public void procString(String str, int num) {
        for (int i = 0; i < num; i++) {
            System.out.print(str);
        }
        System.out.println();
    }
}
