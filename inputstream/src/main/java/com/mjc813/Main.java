package com.mjc813;

import com.mjc813.data.GameType;
import com.mjc813.data.Grade;
import com.mjc813.data.Human;
import com.mjc813.data.NintendoGame;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class Main {
    public static void main(String[] args) {
        Human lsh = new Human("이순신", 19);
        lsh.getGameList().add(new NintendoGame("마리오카트", 50000, Grade.All, GameType.Arcade));
        lsh.getGameList().add(new NintendoGame("루이지카트", 40000, Grade.All, GameType.Arcade));
        lsh.getGameList().add(new NintendoGame("젤다올림픽", 30000, Grade.All, GameType.Sport));
        SampleOutputStream();
    }
        public static void SampleOutputStream () {
//		try {
//			OutputStream os = new FileOutputStream("./test.txt");
//			os.write(10);
//			os.write(20);
//			os.write(30);
//			os.flush();
//			os.close();
//		} catch (Exception e) {
//			System.err.println(e);
//		} finally {
//		}

            OutputStream os = null;
            try {
                os = new FileOutputStream("./test.txt");
                os.write(10);
                os.write(20);
                os.write(30);
                os.flush();
            } catch (Exception e) {
                System.err.println(e);
            } finally {
                try {
                    if (os != null) {
                        os.close();
                    }
                } catch (IOException e) {
                }
            }
        }
    }
