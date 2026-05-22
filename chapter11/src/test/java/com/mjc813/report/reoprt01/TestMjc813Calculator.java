package com.mjc813.report.reoprt01;

import com.mjc813.report.report01.Mjc813Calculator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMjc813Calculator {
    @Test
    public void testSum() {
        Mjc813Calculator mjc813cal = new Mjc813Calculator();
        NullPointerException exception1 = assertThrows(NullPointerException.class, () -> {
            mjc813cal.sum(null);
        });
        NullPointerException exception2 = assertThrows(NullPointerException.class, () -> {
            mjc813cal.sum(3, null, 1);
        });
        assertThat(mjc813cal.strongSum(3, null, 1)).isEqualTo(4);

        NullPointerException exception3 = assertThrows(NullPointerException.class, () -> {
            mjc813cal.sum(null, 0, 1);
        });
        NullPointerException exception4 = assertThrows(NullPointerException.class, () -> {
            mjc813cal.sum(new Integer[] {3, null, 1}, 0, 1);
        });
        NullPointerException exception5 = assertThrows(NullPointerException.class, () -> {
            mjc813cal.sum(new Integer[] {3, 6, 7, 9, 11, 13}, null, 1);
        });
        NullPointerException exception6 = assertThrows(NullPointerException.class, () -> {
            mjc813cal.sum(new Integer[] {3, 6, 7, 9, 11, 13}, 1, null);
        });
        ArrayIndexOutOfBoundsException exception7 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            mjc813cal.sum(new Integer[] {3, 6, 7, 9, 11, 13}, -1, 3);
        });
        ArrayIndexOutOfBoundsException exception8 = assertThrows(ArrayIndexOutOfBoundsException.class, () -> {
            mjc813cal.sum(new Integer[] {3, 6, 7, 9, 11, 13}, 4, 6);
        });

        assertThat(mjc813cal.sum(1, 2, 3, 4, 5)).isEqualTo(15);
        assertThat(mjc813cal.sum(new Integer[] {3, 6, 7, 9, 11, 13}, 2, 4)).isEqualTo(27);
    }
}

