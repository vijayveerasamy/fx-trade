package com.vijay.fxtrade.service;

import com.vijay.fxtrade.model.Instruction;
import com.vijay.fxtrade.model.InstructionType;

import java.util.List;

public interface FxTransaction {

    public void trade(Instruction instruction);

    public List getTrades();

    public void printReportTrades(InstructionType instructionType);

}
