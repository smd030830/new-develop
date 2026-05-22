package com.mjc813.taskreview;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClientApp extends Thread {
    private Socket socket;
    private DataInputStream dis;
    private BufferedWriter bw;

    public ClientApp() throws IOException {
        this.socket = new Socket(); // 클라이언트의 통신용 소켓 생성
    }

    public void connect() throws IOException {
//		this.socket.bind(new InetSocketAddress("localhost", 59998)); // ip 와 포트 정보를 bind 하고 접속 시도한다.
        this.socket.connect(new InetSocketAddress("localhost", 59997));
        // 해당 ip 와 포트로 접속을 시도한다.

        this.dis = new DataInputStream(this.socket.getInputStream());
        this.bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
    }

    public void close() throws IOException {
        try {
            this.bw.close();
        } catch (Exception ex) {
        }
        try {
            this.dis.close();
        } catch (Exception ex) {
        }
        try {
            this.socket.close();
        } catch (Exception ex) {
        }
    }

    public String read() throws IOException {
        String str = null;
//		try {
        str = this.dis.readUTF();
//		} catch (IOException e) {
//			System.err.println("데이터 입력 받을 수 없습니다.");
//			System.exit(-82429);
//		}
        return str;
    }
    @Override
    public void run() {
        try {
            while (true) {
                String msg = read();
                if (msg == null) break;
                System.out.println("SERVER:" + msg);
            }
        } catch (IOException e) {
            System.err.println("수신 종료: " + e.getMessage());
        }
    }


    public void send(String msg) {
        try {
            bw.write(msg);
            bw.newLine();
            bw.flush();
        } catch (Exception ex) {
        }
    }

    public static void main(String[] args) {
        Scanner scanner;
        ClientApp ca = null;
        try {
            scanner = new Scanner(System.in);
            ca = new ClientApp();
            ca.connect();

            while(true) {
                String str = scanner.nextLine();
                ca.send(str);

                String msg = ca.read();
                System.out.println("SERVER:" + msg);
            }
        } catch (Exception ex) {
            System.err.println(ex.toString());
        } finally {
            try {
                ca.close();
            } catch (Exception ex) {
            }
        }
    }
}
