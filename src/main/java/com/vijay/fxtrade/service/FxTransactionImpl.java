package com.vijay.fxtrade.service;

import com.vijay.fxtrade.model.Instruction;
import com.vijay.fxtrade.model.InstructionType;

import java.util.ArrayList;
import java.util.List;

public class FxTransactionImpl implements FxTransaction {

    private static final List<Instruction> trades = new ArrayList<Instruction>();

    @Override
    public void trade(Instruction instruction) {
        trades.add(instruction);
    }

    @Override
    public List getTrades() {
        return trades;
    }

    @Override
    public void printReportTrades(InstructionType instructionType) {

    }
}
