package com.mjc813.machine;

/*
*int 데이터형들의 합을 구해서 리턴한다
* @parm items int 형 데이터들을 ,로 구분한 매개변수
* @reeturn 매개변수의 총 합계
 */
public class Calculator {
    public long add(int ... item) {
        long result = 0L;
        for(int j=0;j<item.length;j++){
            result += item[j];
        }
        return result;
    }
}
