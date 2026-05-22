package com.mjc813;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Box<String> box1 = new Box<>();
        box1.content = "Hello";
        String str = box1.content;
        System.out.println(str);

        Box<Integer> box2 = new Box<>();
        box2.content = 100;
        int value = box2.content;
        System.out.println(value);

        Product<Tv,String> product1 = new Product<>();

        product1.setKind(new Tv());
        product1.setModel("스마트TV");

        Tv tv = product1.getKind();
        String tvModel = product1.getModel();

        Product<Car,String> product2 = new Product<>();
        product2.setKind(new Car());
        product2.setModel("SUV자동차");

        Car car =product2.getKind();
        String carModel = product2.getModel();
    }
}