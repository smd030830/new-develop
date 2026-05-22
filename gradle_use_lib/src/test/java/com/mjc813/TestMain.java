package com.mjc813;

import com.google.gson.Gson;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
// import static org.assertj.core.api.Assertions.*;
// import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class TestMain {
    @Test
    public void testMain1() {
//		assertEquals(2, 3-1);
//		assertEquals(4, 2+2);
        assertThat(2+1).isEqualTo(3);
        assertThat(4+2).isEqualTo(6);
    }

    @Test
    public void testMain2() {
//		assertEquals("aaa", "aa" + "a");
        assertThat("aaa").isEqualTo("a"+"aa").isAlphabetic();
    }

    @Test
    public void testGson() {
        Gson gs = new Gson();
        MyClass mc = new MyClass();

        String s = gs.toJson(mc);
        MyClass mc2 = gs.fromJson(s, MyClass.class);
        assertThat(mc2.getMyArray().length).isEqualTo(mc.getMyArray().length);
        assertThat(mc2.getMyArray()).isEqualTo(mc.getMyArray());
    }
}
