package com.vijay.fxtrade.service.impl;

import com.vijay.fxtrade.model.Currency;
import com.vijay.fxtrade.model.Instruction;
import com.vijay.fxtrade.model.InstructionType;
import com.vijay.fxtrade.service.FxTransaction;
import org.javatuples.Pair;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.*;
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

    private static String customFormat(String pattern, double value ) {
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        return myFormatter.format(value);
    }

    @Override
    public void printReportTrades(InstructionType instructionType) {

        System.out.println("____________________Daily "+ instructionType.description().toLowerCase() +" report____________________");
        System.out.println("");

        List<Map.Entry<Pair<String, DoubleSummaryStatistics>,
                Set<Map.Entry<Pair<Currency, DoubleSummaryStatistics>, Map<LocalDate, BigDecimal>>>>> resultSet =

                trades.stream().filter(ins -> ins.getInstructionType().equals(instructionType))

                        .collect(Collectors.groupingBy(t0 -> t0.getSettlement().getEntity(),
                                Collectors.groupingBy(tc -> tc.getCurrency(),
                                        Collectors.groupingBy(t1 -> t1.getSettlementDate().with(TemporalAdjusters.ofDateAdjuster(d -> d)),
                                                Collectors.reducing(BigDecimal.ZERO, Instruction::getAmount, BigDecimal::add))))
                        ).entrySet().stream()

                        .map(ent ->
                                new AbstractMap.SimpleEntry<Pair<String, DoubleSummaryStatistics>,
                                        Set<Map.Entry<Pair<Currency, DoubleSummaryStatistics>, Map<LocalDate, BigDecimal>>>>(

                                        Pair.with(ent.getKey(), ent.getValue().values().stream().flatMapToDouble(
                                                c -> c.values().stream().mapToDouble(BigDecimal::doubleValue)).summaryStatistics()),

                                        ent.getValue().keySet().stream().map(m ->
                                                new AbstractMap.SimpleEntry<Pair<Currency, DoubleSummaryStatistics>,
                                                        Map<LocalDate, BigDecimal>>(
                                                        Pair.with(m, ent.getValue().get(m).values().stream()
                                                                .mapToDouble(BigDecimal::doubleValue).summaryStatistics()
                                                        ),

                                                        ent.getValue().get(m).entrySet().stream()
                                                       .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                                                                        (e1, e2) -> e2, LinkedHashMap::new))

                                                )).collect(Collectors.toSet())
                                ))
                        .collect(Collectors.toList()).stream().sorted((f1,f2) ->
                        Double.valueOf(f2.getKey().getValue1().getSum()).compareTo(
                            Double.valueOf(f1.getKey().getValue1().getSum()))).collect(Collectors.toList())

                .stream().map(tr -> tr).collect(Collectors.toList());

        String format = "%12s%12s%12s%12s\n";
        System.out.format(format, "Fx Entity", "Currency", "Count", "Total(USD)");

        resultSet.stream().forEach(obj -> {
            System.out.format(format, obj.getKey().getValue0(), "", obj.getKey().getValue1().getCount(), customFormat("0.##", obj.getKey().getValue1().getSum()));
            obj.getValue().stream().forEach(cur ->
                    System.out.format(format, "", cur.getKey().getValue0(), cur.getKey().getValue1().getCount(), customFormat("0.##", cur.getKey().getValue1().getSum()))
            );
        });

        System.out.println("____________________***____________________");
    }

}
