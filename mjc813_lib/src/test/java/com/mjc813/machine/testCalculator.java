package com.mjc813.machine;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.*;

public class testCalculator {
    @Test
    public void testadd(){
        Calculator cal = new Calculator();
//        assertEqual(왼쪽 값, 오른쪽 값) ; 엣날방식
//        assertThat(실제값).isEqualTo(기대하는것).메소드2().메소드3();
        assertThat(cal.add(1,2,3,7,9)).isEqualTo(22L);
        assertThat(cal.add(-33,-77,-909)).isEqualTo(-1019L);
    }
}