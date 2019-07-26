package com.vijay.fxtrade.service.impl;

import com.vijay.fxtrade.model.Instruction;
import com.vijay.fxtrade.model.InstructionType;
import com.vijay.fxtrade.model.Settlement;
import com.vijay.fxtrade.service.FxTransaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FxTransactionImpl implements FxTransaction {

    private static final List<Instruction> trades = new ArrayList<Instruction>();

    @Override
    public void trade(Instruction instruction) {
        trades.add(instruction);
    }

    @Override
    public List<Instruction> getTrades() {
        return trades;
    }

    @Override
    public void printReportTrades(InstructionType instructionType) {

        System.out.println("____________________Daily "+ instructionType.description().toLowerCase() +" report____________________");
        System.out.println("");

        List<Settlement> combined = trades.stream()
                                        .filter(ins -> ins.getInstructionType().equals(instructionType))
                                        .collect(Collectors.groupingBy(t ->t.getSettlement(),
                                            Collectors.reducing(BigDecimal.ZERO,
                                            Instruction::getAmount,
                                            BigDecimal::add)))
                                        .entrySet()
                                        .stream()
                                        .map(Settlement::withAmount)
                                        .sorted((o1,o2) -> o2.getAmount().compareTo(o1.getAmount())).collect(Collectors.toList());

        combined.stream().forEach(System.out::println);

        System.out.println("____________________***____________________");
        System.out.println("");
    }
}
