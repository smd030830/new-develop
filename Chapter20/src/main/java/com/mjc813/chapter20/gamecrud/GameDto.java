package com.mjc813.chapter20.gamecrud;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GameDto {
    private Integer id;
    private String name;
    private String genre;
    private String grade;
    private Integer price;
    private String imgUrl;
}
