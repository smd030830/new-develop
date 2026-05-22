package com.mjc813.chapter15;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        UseObject uo1 = new UseObject();
        UseObject uo2 = new UseObject();
        if (uo1.equals(uo2)) {
            System.out.println("same object");
        }else  {
            System.out.println("different object");
        }

        Set<Member> set = new HashSet<Member>();

        set.add(new Member("홍길동",30));
        set.add(new Member("홍길동",30));
        System.out.println("set: " + set.size());



    }
}