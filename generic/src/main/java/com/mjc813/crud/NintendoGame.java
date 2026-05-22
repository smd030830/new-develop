package com.mjc813.crud;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class NintendoGame {
    private String name;
    private Grade grade;
    private Integer price;
}
