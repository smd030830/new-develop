package com.mjc813;

import com.mjc813.example.Avante;
import com.mjc813.example.Driver;
import com.mjc813.example.Driving;
import com.mjc813.example.DrivingInterface;
import com.mjc813.example.exception.AvanteNullException;
import com.mjc813.example.exception.DriverNullException;
import com.mjc813.example.exception.ParmeterNullException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Casher casher = new Casher();
        try {
            Integer pedal = null;
            casher.calculMoney(1);
            TestDriving(pedal);
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("ArrayIndexOutOfBoundsException : " + ex.getMessage());
        } catch (NullPointerException ex) {
            System.err.println("NullPointerException : " + ex.getMessage());
        } catch (Exception ex) {
            System.err.println("Exception : " + ex.getMessage());
        } finally {
            System.out.println("finish execute !");
        }
    }

    public static void doSomeThing(DrivingInterface drivInterf) throws ParmeterNullException, AvanteNullException, DriverNullException {
        try {
            drivInterf.doSomething(4);
        } catch ( ParmeterNullException ex ) {
            System.err.println(ex.toString());
        } catch ( DriverNullException ex ) {
            System.err.println(ex.toString());
        } catch ( AvanteNullException ex ) {
            System.err.println(ex.toString());
        }
    }

    public static void doSomething(DrivingInterface di) {
        try {
            di.doSomething(4);
        } catch ( ParmeterNullException ex ) {
            System.err.println(ex.toString());
        } catch ( DriverNullException ex ) {
            System.err.println(ex.toString());
        } catch ( AvanteNullException ex ) {
            System.err.println(ex.toString());
        }
    }

    public static void TestDriving(Integer pedal) {
        Driving driving = new Driving();
        try {
            driving.go(pedal);
            driving.go(4);
            driving.go(8);
            driving.stop(pedal);
            driving.stop(3);
            driving.stop(0);
        } catch ( ParmeterNullException | DriverNullException | AvanteNullException ex ) {
            System.err.println(ex.toString());
        }

        try {
            driving.go(4);
        } catch ( ParmeterNullException ex ) {
            System.err.println(ex.toString());
        } catch ( DriverNullException ex ) {
            System.err.println(ex.toString());
            driving.setLsh(new Driver("이승협"));
        } catch ( AvanteNullException ex ) {
            System.err.println(ex.toString());
            driving.setAvanteMD(new Avante("MD7", "red"));
        }

        try {
            driving.go(8);
        } catch ( ParmeterNullException ex ) {
            System.err.println(ex.toString());
        } catch ( DriverNullException ex ) {
            System.err.println(ex.toString());
            driving.setLsh(new Driver("이승협"));
        } catch ( AvanteNullException ex ) {
            System.err.println(ex.toString());
            driving.setAvanteMD(new Avante("MD7", "red"));
        }

        try {
            driving.stop(pedal);
        } catch ( ParmeterNullException ex ) {
            System.err.println(ex.toString());
        } catch ( DriverNullException ex ) {
            System.err.println(ex.toString());
            driving.setLsh(new Driver("이승협"));
        } catch ( AvanteNullException ex ) {
            System.err.println(ex.toString());
            driving.setAvanteMD(new Avante("MD7", "red"));
        }

        try {
            driving.stop(3);
        } catch ( ParmeterNullException ex ) {
            System.err.println(ex.toString());
        } catch ( DriverNullException ex ) {
            System.err.println(ex.toString());
            driving.setLsh(new Driver("이승협"));
        } catch ( AvanteNullException ex ) {
            System.err.println(ex.toString());
            driving.setAvanteMD(new Avante("MD7", "red"));
        }

        try {
            driving.stop(0);
        } catch ( ParmeterNullException ex ) {
            System.err.println(ex.toString());
        } catch ( DriverNullException ex ) {
            System.err.println(ex.toString());
            driving.setLsh(new Driver("이승협"));
        } catch ( AvanteNullException ex ) {
            System.err.println(ex.toString());
            driving.setAvanteMD(new Avante("MD7", "red"));
        }

        doSomething(new DrivingInterface() {
            @Override
            public void doSomething(Integer pedal) throws ParmeterNullException, AvanteNullException, DriverNullException {
                driving.go(pedal);
            }
        });

        doSomething(new DrivingInterface() {
            @Override
            public void doSomething(Integer pedal) throws ParmeterNullException, AvanteNullException, DriverNullException {
                driving.go(4);
            }
        });

        doSomething(new DrivingInterface() {
            @Override
            public void doSomething(Integer pedal) throws ParmeterNullException, AvanteNullException, DriverNullException {
                driving.go(8);
            }
        });
    }
}