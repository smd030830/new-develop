package com.mjc813.taskreview;

import com.mjc813.taskreview.ServerCommuicateSocket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ServerApp {
    private ServerSocket serverSocket;

    public ServerApp() throws IOException {
        this.serverSocket = new ServerSocket(59997);
    }

    public Socket accept() throws IOException {
        return this.serverSocket.accept();
    }

    public void close() throws IOException {
        this.serverSocket.close();
    }

    public static void main(String[] args) {
        ServerApp sa = null;
        Scanner scanner = null;
        ServerCommuicateSocket scs = null;

        try {
            scanner = new Scanner(System.in);
            sa = new ServerApp();
            System.out.println("서버 대기 중...");
            Socket socket = sa.accept();
            scs = new ServerCommuicateSocket(socket);

            while(true) {
                String str = scanner.nextLine();
                scs.send(str);
            }
        } catch (Exception e) {
            System.err.println(e.toString());
        } finally {
            if (scs != null) scs.close();
            try {
                if (sa != null) sa.close();
            } catch (Exception ex) {
            }
        }
    }
}