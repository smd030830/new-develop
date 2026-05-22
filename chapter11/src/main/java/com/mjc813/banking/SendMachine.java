package com.mjc813.banking;

public class SendMachine {
    public boolean isActive() throws MachineNotWorkingException {
        if ( Math.random() >= 0.3 ) {
            return true;
        } else {
            throw new MachineNotWorkingException("Machine broken !!");
        }
    }
}
