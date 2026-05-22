package com.mjc813.crud;

import com.google.gson.Gson;
import com.mjc813.generic.generic;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;


public class TestGeneric {
    @Test
    public void TestGeneric() {
        generic<String> generic = new generic<>();
        assertThat(generic).isNotNull();
        assertThat(generic.size()).isEqualTo(0);

        generic.add("test01");
        generic.add("test02");

        String remove1 = generic.remove(3);
        assertThat(generic.size()).isEqualTo(2);
        assertThat(remove1).isNull();

        String remove2 = generic.remove(0);
        assertThat(generic.size()).isEqualTo(1);
        assertThat(remove2).isEqualTo("test01");

        generic.add("test03");
        generic.add("test04");
        assertThat(generic.size()).isEqualTo(3);
        String str3 = generic.get(0);
        assertThat(str3).isEqualTo("test02");

        generic.set(0, "test05");
        String str1 = generic.get(0);
        assertThat(str1).isEqualTo("test05");
    }
}
