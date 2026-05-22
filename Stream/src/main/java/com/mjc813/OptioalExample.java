package com.mjc813;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class OptioalExample {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();

        OptionalDouble optional = list.stream().mapToInt(Integer :: intValue).average();

        if (optional.isPresent()) {
            System.out.println("방법1 평균 : " + optional.getAsDouble());
        }else
            System.out.println("방법1 평균 : 0.0");

        double avg = list.stream().mapToInt(Integer :: intValue).average().orElse(0.0);
        System.out.println("방법2 평균 : " + avg);

        list.stream().mapToInt(Integer :: intValue).average().ifPresent(a -> System.out.println("방법 3 평균 : "+a));


        List<Student> totalList = new ArrayList<>();
        totalList.add(new Student("홍길동", "남", 92));
        totalList.add(new Student("김수영", "여", 87));
        totalList.add(new Student("감사바", "남", 95));
        totalList.add(new Student("오해영", "여", 93));

        List<Student> maleList = totalList.stream()
                .filter(s->s.getSex().equals("남"))
                .toList();
        maleList.forEach(s-> System.out.println(s.getName()));
        System.out.println();

        Map<String,Integer> map = totalList.stream().collect(
                Collectors.toMap(
                        s -> s.getName(),
                        s->s.getScore()
                )
        );
        System.out.println(map);
    }
}
