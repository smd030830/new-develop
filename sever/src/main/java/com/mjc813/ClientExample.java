package com.mjc813;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientExample {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("localhost", 50001);

            System.out.println("클라이언트 연결 성공");

            socket.close();
            System.out.println("클라이언트 연결 끓음");
        }catch (UnknownHostException e){

        }catch (IOException e){

        }
    }
}
