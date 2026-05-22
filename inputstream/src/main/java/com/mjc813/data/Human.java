package com.mjc813.data;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class Human {
    private String name;
    private int age;
    private GameList<NintendoGame> gameList;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
        this.gameList = new GameList<>();
    }
}
