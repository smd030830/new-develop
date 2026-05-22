package com.mjc813.taskreview;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class ServerCommuicateSocket {
    private Socket socket;
    private DataInputStream dis;
    private DataOutputStream dos;

    public ServerCommuicateSocket(Socket socket) {
        this.socket = socket;
        try {
            this.dis = new DataInputStream(socket.getInputStream());
            this.dos = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
        }
    }

    public void send(String msg) {
        try {
            this.dos.writeUTF(msg);
            this.dos.flush();
        } catch (Exception e) {
        }
    }

    public String read() {
        String str = "";
        try {
            str = this.dis.readUTF();
        } catch (Exception e) {
        }
        return str;
    }

    public void close() {
        try {
            if (this.dos != null) this.dos.close();
        } catch (Exception e) {
        }
        try {
            if (this.dis != null) this.dis.close();
        } catch (Exception e) {
        }
        try {
            if (this.socket != null) this.socket.close();
        } catch (Exception e) {
        }
    }
}