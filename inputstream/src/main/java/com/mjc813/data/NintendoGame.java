package com.mjc813.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString

public class NintendoGame {
    private String name;
    private Integer price;
    private Grade grade;
    private GameType type;
}
