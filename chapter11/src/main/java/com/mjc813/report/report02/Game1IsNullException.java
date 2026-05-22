package com.mjc813.report.report02;

public class Game1IsNullException extends RuntimeException {
    public Game1IsNullException() {
        super("게임 소프트웨어가 설치되지 않았습니다.");
    }

    public Game1IsNullException(String message) {
        super(message);
    }
}