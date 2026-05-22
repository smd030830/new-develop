package com.mjc813.example;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class Avante {
    private final String modelName;
    private final String color;
    private int speed = 0 ;

    public void setSpeed(int sp) {
        if ( sp < 0 || sp > 170 ) {
            return;
        }
        this.speed = sp;
    }
}
