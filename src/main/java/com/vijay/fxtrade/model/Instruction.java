package com.vijay.fxtrade.model;

import com.vijay.fxtrade.exception.InvalidTradeInstruction;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Instruction {
    private String entity;
    private InstructionType instructionType;
    private Currency currency;
    private LocalDate instructionDate;
    private LocalDate settlementDate;
    private BigDecimal agreedFxRate;
    private Integer units;
    private BigDecimal pricePerUnit;

    public Instruction(final String entity, final InstructionType instructionType, final Currency currency,
                       final LocalDate instructionDate, final LocalDate settlementDate,
                       final BigDecimal agreedFxRate, final BigDecimal pricePerUnit, final Integer units) throws InvalidTradeInstruction {

        if ( settlementDate.isBefore(instructionDate) ) {
            throw new InvalidTradeInstruction("Invalid settlement date");
        }

        this.entity=entity;
        this.instructionType=instructionType;
        this.currency=currency;
        this.instructionDate=instructionDate;
        this.settlementDate=settlementDate;
        setSettlementDate(settlementDate);
        this.agreedFxRate=agreedFxRate;
        this.pricePerUnit=pricePerUnit;
        this.units=units;
    }

    private final void setSettlementDate(LocalDate settlementDate) {
        if ( currency==Currency.AED || currency==Currency.SAR ) {
            if(settlementDate.getDayOfWeek()==DayOfWeek.FRIDAY || settlementDate.getDayOfWeek()==DayOfWeek.SATURDAY)
                this.settlementDate=settlementDate.plusDays((long) DayOfWeek.SUNDAY.getValue()-settlementDate.getDayOfWeek().getValue());
        }
        else {
            if(settlementDate.getDayOfWeek()==DayOfWeek.SUNDAY || settlementDate.getDayOfWeek()==DayOfWeek.SATURDAY) {
                this.settlementDate = settlementDate.plusDays((long) DayOfWeek.SUNDAY.getValue() - settlementDate.getDayOfWeek().getValue() + 1);
            }
        }
    }
}