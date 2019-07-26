package com.vijay.fxtrade.model;

import com.vijay.fxtrade.exception.InvalidTradeInstruction;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Instruction {
    private String entity;
    private InstructionType instructionType;
    private Currency currency;
    private LocalDate instructionDate;
    private LocalDate settlementDate;
    private BigDecimal agreedFxRate;
    private Integer units;
    private BigDecimal pricePerUnit;
    private BigDecimal amount;

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
        amount = pricePerUnit.multiply(BigDecimal.valueOf((long) units)).multiply(agreedFxRate);
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

    public String getEntity() {
        return entity;
    }

    public InstructionType getInstructionType() {
        return instructionType;
    }

    public Currency getCurrency() {
        return currency;
    }

    public LocalDate getInstructionDate() {
        return instructionDate;
    }

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public BigDecimal getAgreedFxRate() {
        return agreedFxRate;
    }

    public Integer getUnits() {
        return units;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Instruction to " + this.instructionType + " from Entity:\"" + this.entity + "\", Currency:"
                + this.currency + ", amount:" + this.amount + " with requested settlement on "+ this.settlementDate.format(DateTimeFormatter.ofPattern("dd LLLL yyyy"));
    }

    public Settlement getSettlement() {
        return new Settlement(this.entity, this.instructionType, this.currency,
                this.settlementDate);
    }
}