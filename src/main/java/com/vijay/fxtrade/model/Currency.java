package com.vijay.fxtrade.model;

public enum Currency {
    AED("AED"),
    HKD("HKD"),
    SAR("SAR"),
    SGD("SGD");

    private String value;

    public String value() { return value; }

    Currency(String value) { this.value = value; }
}
