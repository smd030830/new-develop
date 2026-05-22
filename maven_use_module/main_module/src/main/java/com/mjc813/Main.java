package com.mjc813;

public class Main {
    public static void main(String[] args) {

        Student student = new Student("이순신","mjc9283819", Major.InfoTelecom);
        TransJson tj = new TransJson();

        String strJson = tj.toJsonStringFromObject(student);
        System.out.println(strJson);

        Object res = tj.toObjectFromJsonString(strJson, Student.class);
        if (res instanceof Student) {
            System.out.println(res);
            System.out.printf("name:%s, hakbun:%s, major:%s"
                    , ((Student)res).getName()
                    , ((Student)res).getHakbun()
                    , ((Student)res).getMajor()
            );
        }
        System.out.println(tj.hashCode());
        System.out.println(res.hashCode());
    }
}