package com.mjc813.banking;

public interface IMachine {
    boolean isActive() throws MachineNotWorkingException;
}
