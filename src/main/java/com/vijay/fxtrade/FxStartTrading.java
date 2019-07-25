package com.vijay.fxtrade;

import com.vijay.fxtrade.model.Currency;
import com.vijay.fxtrade.model.Instruction;
import com.vijay.fxtrade.model.InstructionType;
import com.vijay.fxtrade.service.FxTransaction;
import com.vijay.fxtrade.service.FxTransactionImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FxStartTrading {
    public static void main(String[] args) {
        FxTransaction fxTransaction = new FxTransactionImpl();

        try {
            //Singapore Ship Yard
            fxTransaction.trade(new Instruction("SSY", InstructionType.BUY, Currency.AED,
                    LocalDate.of(2019, 07, 17), LocalDate.of(2019, 07, 18),
                    new BigDecimal(0.27), new BigDecimal(0.27), 500));
            fxTransaction.trade(new Instruction("SSY", InstructionType.BUY, Currency.AED,
                    LocalDate.of(2019, 07, 16), LocalDate.of(2019, 07, 19),
                    new BigDecimal(0.27), new BigDecimal(0.27), 600));
            fxTransaction.trade(new Instruction("SSY", InstructionType.BUY, Currency.AED,
                    LocalDate.of(2019, 07, 15), LocalDate.of(2019, 07, 20),
                    new BigDecimal(0.27), new BigDecimal(0.27), 400));

            //Dubai Port Authority
            fxTransaction.trade(new Instruction("DPA", InstructionType.BUY, Currency.SGD,
                    LocalDate.of(2019, 07, 19), LocalDate.of(2019, 07, 19),
                    new BigDecimal(0.27), new BigDecimal(0.27), 700));
            fxTransaction.trade(new Instruction("DPA", InstructionType.BUY, Currency.SGD,
                    LocalDate.of(2019, 07, 16), LocalDate.of(2019, 07, 21),
                    new BigDecimal(0.27), new BigDecimal(0.27), 300));
            fxTransaction.trade(new Instruction("DPA", InstructionType.BUY, Currency.SGD,
                    LocalDate.of(2019, 07, 15), LocalDate.of(2019, 07, 20),
                    new BigDecimal(0.27), new BigDecimal(0.27), 500));
        }
        catch(Exception e) {e.printStackTrace();}

        List<Instruction> trades = fxTransaction.getTrades();

        System.out.println(trades.size());

    }
}
