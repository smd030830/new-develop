package excoding;

import javax.naming.InsufficientResourcesException;

import static excoding.MyResource.*;
//import static excoding.ExceptionHandlingExample1.printLength;
import static excoding.ExceptionHandlingExample2.printLength;

public class Main {
    public static void main(String[] args) {
        System.out.println("program start");
        printLength("This is Java");
        printLength(null);
        System.out.println("program end");

//        try{
//            Class.forName("java.lang.String");
//            System.out.println("class java.lang.String is true");
//        }catch(ClassNotFoundException e){
//            e.printStackTrace();
//        }
//        System.out.println();
//
//        try{
//            Class.forName("java.lang.String2");
//            System.out.println("class java.lang.String2 is true");
//        }catch(ClassNotFoundException e){
//            e.printStackTrace();
//        }

        try(MyResource res = new MyResource("A")){
            String data = res.read1();
            int value = Integer.parseInt(data);
        }catch (Exception e){
            System.out.println("예외처리"+e.getMessage());
        }
        System.out.println();

        try(MyResource res = new MyResource("A")){
            String data = res.read2();
            int value = Integer.parseInt(data);
        }catch (Exception e){
            System.out.println("예외처리"+e.getMessage());
        }
        System.out.println();

        MyResource res = new MyResource("A");
        MyResource res2 = new MyResource("B");
        try {
            String data = res.read1();
            String data2 = res.read2();
        }catch (Exception e){
            System.out.println("에외처리: "+e.getMessage() );
        }

        try{
            findClass();
        } catch (ClassNotFoundException e) {
            System.out.println("예외처리: "+e.toString());
        }

        Account account = new Account();
        account.deposit(10000);
        System.out.println("예금액" + account.getBalance());

        try {
            account.withdraw(30000);
        }catch (InsufficientResourcesException e){
            String message = e.getMessage();
            System.out.println(message);
        }
    }
    public static void findClass() throws ClassNotFoundException {
        Class.forName("java.lang.String3");
    }
}
