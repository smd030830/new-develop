package com.mjc813.crud;

import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class TestCrud {
    @Test
    public void testCRUD() {
        CRUD<String> crudClass = new CrudClass<>();
        assertThat(crudClass).isNotNull();
        assertThat(crudClass.size()).isEqualTo(0);

        crudClass.add("test1");
        crudClass.add("test2");
        assertThat(crudClass.size()).isEqualTo(2);

        String remove1 = crudClass.remove(3);
        assertThat(crudClass.size()).isEqualTo(2);
        assertThat(remove1).isNull();

        String remove2 = crudClass.remove(0);
        assertThat(crudClass.size()).isEqualTo(1);
        assertThat(remove2).isEqualTo("test1");

        crudClass.add("test3");
        crudClass.add("test4");
        assertThat(crudClass.size()).isEqualTo(3);
        String str3 = crudClass.get(0);
        assertThat(str3).isEqualTo("test2");

        crudClass.set(0, "testtest");
        String str1 = crudClass.get(0);
        assertThat(str1).isEqualTo("testtest");
    }

    @Test
    public void testJsonCrud() {
        Gson gson = new Gson();
        CRUD<NintendoGame> crudClass = new CrudClass<>();
        assertThat(crudClass).isNotNull();
        assertThat(crudClass.size()).isEqualTo(0);

        crudClass.add(new NintendoGame("젤다의기사", Grade.ALL, 50000));
        crudClass.add(new NintendoGame("마리오카트", Grade.ALL, 48000));
        assertThat(crudClass.size()).isEqualTo(2);

        NintendoGame obj = crudClass.get(0);
        String strJson1 = crudClass.getJson(0);

        NintendoGame game = gson.fromJson(strJson1, NintendoGame.class);
        assertThat(obj.getName()).isEqualTo(game.getName());
        assertThat(obj.getGrade()).isEqualTo(game.getGrade());
        assertThat(obj.getPrice()).isEqualTo(game.getPrice());

        String strJson2 = crudClass.getJsonAllItems();
        NintendoGame[] nintendoGames = gson.fromJson(strJson2, NintendoGame[].class);
        assertThat(nintendoGames.length).isEqualTo(crudClass.size());
    }
}
