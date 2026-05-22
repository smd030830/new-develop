package com.mjc813.report.reoprt02;

import com.mjc813.report.report02.BatteryLessThan5Exception;
import com.mjc813.report.report02.Game1IsNullException;
import com.mjc813.report.report02.MarioGame;
import com.mjc813.report.report02.Mjc813GameMachineV1;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestMjc813GameMachineV1 {
    @Test
    public void TestRunGame1() {
        Mjc813GameMachineV1 mjc813gameM1 = new Mjc813GameMachineV1();
        BatteryLessThan5Exception exception1 = assertThrows(BatteryLessThan5Exception.class, () -> {
            mjc813gameM1.runGame1();
        });

        Mjc813GameMachineV1 mjc813gameM2 = new Mjc813GameMachineV1(3, new MarioGame());
        BatteryLessThan5Exception exception2 = assertThrows(BatteryLessThan5Exception.class, () -> {
            mjc813gameM2.runGame1();
        });

        Mjc813GameMachineV1 mjc813gameM3 = new Mjc813GameMachineV1(15, null);
        Game1IsNullException exception3 = assertThrows(Game1IsNullException.class, () -> {
            mjc813gameM3.runGame1();
        });

        Mjc813GameMachineV1 mjc813gameM4 = new Mjc813GameMachineV1(15, new MarioGame());
        mjc813gameM4.runGame1();
    }
}
