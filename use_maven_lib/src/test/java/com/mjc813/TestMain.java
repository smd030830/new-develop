package com.mjc813;

import com.google.gson.Gson;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.*;

public class TestMain {
    @Test
    public void testMain(){
        assertThat(2).isEqualTo(3-1);
    }
    @Test
    public void testMain2(){
        assertThat("aaa").isEqualTo("aa"+"a");
    }
    @Test
    public void testGson(){
        Gson gs = new Gson();
        Myclass mc = new Myclass();
        String s = gs.toJson(mc);

        Myclass mc2 = gs.fromJson(s, Myclass.class);
        assertThat(mc2.getMyarray().length).isEqualTo(mc.getMyarray().length);
        assertThat(mc2.getMyarray()).isEqualTo(mc.getMyarray());
    }
}
