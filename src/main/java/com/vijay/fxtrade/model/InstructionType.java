package com.vijay.fxtrade.model;

public enum InstructionType {
    BUY(1),
    SELL(2);

    private final int value;

    public int value() { return value; }

    InstructionType(int value) { this.value = value; }
}
