package com.mjc813.task1;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ClientApp {
    private Socket socket;

    public ClientApp() throws IOException {
        this.socket = new Socket(); // 클라이언트의 통신용 소켓 생성
    }

    public void connect() throws IOException {
//		this.socket.bind(new InetSocketAddress("localhost", 59999)); // ip 와 포트 정보를 bind 하고 접속 시도한다.
        this.socket.connect(new InetSocketAddress("localhost", 59999));
        // 해당 ip 와 포트로 접속을 시도한다.
    }

    public void close() throws IOException {
        this.socket.close();
        // 클라이언트 통신 소켓을 닫는다.
    }

    public String read() {
        String str = null;
        try {
//			byte[] buf = new byte[1024];
//			int count = this.socket.getInputStream().read(buf);
//			str = new String(buf);
            DataInputStream dis = new DataInputStream(this.socket.getInputStream());
            str = dis.readUTF();
        } catch (IOException e) {
            System.err.println("데이터 입력 받을 수 없습니다.");
        }
        return str;
    }

    public void send(String msg) {
        try {
//			byte[] buf = msg.getBytes();
//			this.socket.getOutputStream().write(buf);
//			this.socket.getOutputStream().flush();
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream()));
            bw.write(msg);
            bw.newLine();
            bw.flush();
        } catch (Exception ex) {
        }
    }

    public static void main(String[] args) {
        try {
            ClientApp ca = new ClientApp();
            ca.connect();

            String msg = ca.read();
            System.out.println("SERVER:" + msg);

            ca.send("반갑다. 내가 왔노라");
            ca.close();
        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }
}
