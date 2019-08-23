package com.vijay.fxtrade.model;

import com.vijay.fxtrade.exception.InvalidTradeInstruction;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static java.util.Map.Entry;

public class Settlement {
    private String entity;
    private InstructionType instructionType;
    private Currency currency;
    private LocalDate settlementDate;
    private BigDecimal amount;

    public Settlement(final String entity, final InstructionType instructionType, final Currency currency,
                       final LocalDate settlementDate) {

        this.entity=entity;
        this.instructionType=instructionType;
        this.currency=currency;
        this.settlementDate=settlementDate;
    }

    public Settlement(final String entity, final InstructionType instructionType, final Currency currency,
                      final LocalDate settlementDate, final BigDecimal amount) {

        this.entity=entity;
        this.instructionType=instructionType;
        this.currency=currency;
        this.settlementDate=settlementDate;
        this.amount = amount;
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

    public LocalDate getSettlementDate() {
        return settlementDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static Settlement withAmount(Entry<Settlement, BigDecimal> e) {
        return new Settlement(e.getKey().entity, e.getKey().instructionType, e.getKey().currency,
                e.getKey().settlementDate, e.getValue());
    }

    @Override
    public String toString() {
        return  "Entity:\"" + this.entity + "\", currency:"
                + this.currency + ", amount:" + this.amount + " with settlement on "+ this.settlementDate.format(DateTimeFormatter.ofPattern("dd LLLL yyyy"));
    }
}
