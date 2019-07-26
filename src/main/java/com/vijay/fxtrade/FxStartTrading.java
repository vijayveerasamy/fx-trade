package com.vijay.fxtrade;

import com.vijay.fxtrade.model.Currency;
import com.vijay.fxtrade.model.Instruction;
import com.vijay.fxtrade.model.InstructionType;
import com.vijay.fxtrade.service.FxTransaction;
import com.vijay.fxtrade.service.impl.FxTransactionImpl;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class FxStartTrading {

    public static void main(String[] args) {

        FxTransaction fxTransaction = new FxTransactionImpl();

        try {

            fxTransaction.trade(new Instruction("SSY", InstructionType.BUY, Currency.AED,
                    LocalDate.now().minusWeeks(1), LocalDate.now().minusWeeks(1).with(TemporalAdjusters.next(DayOfWeek.WEDNESDAY)),
                  BigDecimal.valueOf(0.27), BigDecimal.valueOf(1.05), 500));
            fxTransaction.trade(new Instruction("SSY", InstructionType.BUY, Currency.AED,
                    LocalDate.now().minusWeeks(1), LocalDate.now().minusWeeks(1).with(TemporalAdjusters.next(DayOfWeek.FRIDAY)),
                    BigDecimal.valueOf(0.27), BigDecimal.valueOf(1.06), 600));
            fxTransaction.trade(new Instruction("SSY", InstructionType.BUY, Currency.AED,
                    LocalDate.now().minusWeeks(1), LocalDate.now().minusWeeks(1).with(TemporalAdjusters.next(DayOfWeek.SATURDAY)),
                    BigDecimal.valueOf(0.27), BigDecimal.valueOf(1.04), 400));


            fxTransaction.trade(new Instruction("DPA", InstructionType.BUY, Currency.SGD,
                    LocalDate.of(2019, 07, 19), LocalDate.of(2019, 07, 19),
                    BigDecimal.valueOf(0.73), BigDecimal.valueOf(1.03), 700));
            fxTransaction.trade(new Instruction("DPA", InstructionType.BUY, Currency.SGD,
                    LocalDate.of(2019, 07, 16), LocalDate.of(2019, 07, 21),
                    BigDecimal.valueOf(0.73), BigDecimal.valueOf(1.05), 300));
            fxTransaction.trade(new Instruction("DPA", InstructionType.BUY, Currency.SGD,
                    LocalDate.of(2019, 07, 15), LocalDate.of(2019, 07, 20),
                    BigDecimal.valueOf(0.73), BigDecimal.valueOf(1.07), 500));


            fxTransaction.trade(new Instruction("SSY", InstructionType.SELL, Currency.AED,
                    LocalDate.of(2019, 07, 17), LocalDate.of(2019, 07, 23),
                    BigDecimal.valueOf(0.26), BigDecimal.valueOf(0.98), 150));
            fxTransaction.trade(new Instruction("SSY", InstructionType.SELL, Currency.AED,
                    LocalDate.of(2019, 07, 16), LocalDate.of(2019, 07, 24),
                    BigDecimal.valueOf(0.26), BigDecimal.valueOf(0.99), 210));
            fxTransaction.trade(new Instruction("SSY", InstructionType.SELL, Currency.AED,
                    LocalDate.of(2019, 07, 15), LocalDate.of(2019, 07, 25),
                    BigDecimal.valueOf(0.26), BigDecimal.valueOf(0.98), 240));


            fxTransaction.trade(new Instruction("DPA", InstructionType.SELL, Currency.SGD,
                    LocalDate.of(2019, 07, 19), LocalDate.of(2019, 07, 23),
                    BigDecimal.valueOf(0.72), BigDecimal.valueOf(0.98), 350));
            fxTransaction.trade(new Instruction("DPA", InstructionType.SELL, Currency.SGD,
                    LocalDate.of(2019, 07, 16), LocalDate.of(2019, 07, 24),
                    BigDecimal.valueOf(0.72), BigDecimal.valueOf(0.97), 200));
            fxTransaction.trade(new Instruction("DPA", InstructionType.SELL, Currency.SGD,
                    LocalDate.of(2019, 07, 15), LocalDate.of(2019, 07, 25),
                    BigDecimal.valueOf(0.72), BigDecimal.valueOf(0.99), 100));
        }
        catch(Exception e) {e.getMessage();}

        fxTransaction.printReportTrades(InstructionType.BUY);

        fxTransaction.printReportTrades(InstructionType.SELL);
    }
}
