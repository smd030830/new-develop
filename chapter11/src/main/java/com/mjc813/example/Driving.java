package com.mjc813.example;

import com.mjc813.example.exception.AvanteNullException;
import com.mjc813.example.exception.DriverNullException;
import com.mjc813.example.exception.ParmeterNullException;
import lombok.*;


@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Driving {
    private Driver lsh;
    private Avante avanteMD;

    public void go(Integer accelatorPedal) throws ParmeterNullException
            , AvanteNullException, DriverNullException {
        if ( accelatorPedal == null ) {
            throw new ParmeterNullException("go", "accelatorPedal is null");
        }
        if ( this.avanteMD == null ) {
            throw new AvanteNullException("avante object is null error");
        }
        if ( this.lsh == null ) {
            throw new DriverNullException(400, "driver object is null error");
        }
        System.out.printf("%s 운전수가 %s 자동차를 운전하여 액셀 %d 밟아서 스피드 %d 가 됐습니다..\n"
                , this.lsh.getName(), this.avanteMD.getModelName()
                , accelatorPedal, this.avanteMD.getSpeed()
        );
    }

    public void stop(Integer breakPedal) throws ParmeterNullException
            , AvanteNullException, DriverNullException {
        if ( breakPedal == null ) {
            throw new ParmeterNullException("stop", "breakPedal is null");
        }
        if ( this.avanteMD == null ) {
            throw new AvanteNullException("avante object is null error");
        }
        if ( this.lsh == null ) {
            throw new DriverNullException(400, "driver object is null error");
        }
        System.out.printf("%s 운전수가 %s 자동차를 운전하여 브레이크 %d 밟아서 스피드 %d 가 됐습니다..\n"
                , this.lsh.getName(), this.avanteMD.getModelName()
                , breakPedal, this.avanteMD.getSpeed()
        );
    }
}