package com.mjc318.lamda;

public class ClassString implements MyString {
    @Override
    public void procString(String str,int num) {
        int lengthStr = str.length();
        if (lengthStr == num) {
            System.out.println("길이가 같다");
        }
    }
}
