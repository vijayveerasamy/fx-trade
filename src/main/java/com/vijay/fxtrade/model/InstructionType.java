package com.vijay.fxtrade.model;

import java.util.HashMap;
import java.util.Map;

public enum InstructionType {
    BUY(1),
    SELL(2);

    private final int value;

    private static final Map<Integer, String> description = new HashMap<Integer, String>(){{
                                                                put(Integer.valueOf(InstructionType.BUY.value()), "Outgoing");
                                                                put(Integer.valueOf(InstructionType.SELL.value()), "Incoming");
                                                            }};
    public int value() { return value; }

    public String description() { return description.get(Integer.valueOf(value)); }

    InstructionType(final int value) { this.value = value; }
}
