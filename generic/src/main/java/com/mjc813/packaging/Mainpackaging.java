package com.mjc813.packaging;

public class Mainpackaging {
    public static void main(String[] args){
        Packaging strPackaging = new Packaging();
        strPackaging.add("이름1");
        strPackaging.add(10);
        Object obj1 = strPackaging.remove();
        System.out.println(obj1);
        System.out.println(strPackaging.remove());

        Packaging humanPackaging = new Packaging();
        humanPackaging.add(new Human("이순신", 53));
        humanPackaging.add(true);
        Object obj2 = humanPackaging.remove();
        System.out.println(obj2);
        System.out.println(humanPackaging.remove());
        try {
            System.out.println(((Human) obj2).getName());
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }

        GenericPackaging<String> strGenericPackaging = new GenericPackaging<String>();
        strGenericPackaging.add("10");
        strGenericPackaging.add("234234");
        String str2 = strGenericPackaging.remove();
        System.out.println(str2);
        System.out.println(strGenericPackaging.remove());

        GenericPackaging<Human> humanGenericPackaging = new GenericPackaging<Human>();
        humanGenericPackaging.add(new Human("홍길동", 19));
        humanGenericPackaging.add(new Human("이말자", 32));
        Human hum2 = humanGenericPackaging.remove();
        System.out.println(hum2);
        System.out.println(humanGenericPackaging.remove());
    }
}
