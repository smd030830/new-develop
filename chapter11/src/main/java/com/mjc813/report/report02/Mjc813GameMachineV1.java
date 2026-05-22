package com.mjc813.report.report02;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Mjc813GameMachineV1 {
    private int battery = 0;
    private MarioGame game1;

    public void runGame1() throws BatteryLessThan5Exception, Game1IsNullException {
        if (this.battery < 5) {
            throw new BatteryLessThan5Exception();
        }

        if (this.game1 == null) {
            throw new Game1IsNullException();
        }
        game1.runGame();
    }
}