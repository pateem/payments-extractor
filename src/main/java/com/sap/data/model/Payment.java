
package com.sap.data.model;

import com.sap.data.validator.IBANValidator;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Payment {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final Config config = ConfigFactory.load("application");
    public static final List<String> validConstantSymbols = config.getStringList("app.validConstantSymbols");

    public final String IBAN;
    public final String amount;
    public final String variableSymbol;
    public final String constantSymbol;
    public final String specificSymbol;
    public final String dueDate;
    public final String note;

    private Payment(String IBAN, String amount, String variableSymbol, String constantSymbol, String specificSymbol, LocalDate localDate, String note) {
        this.IBAN = IBAN;
        this.amount = amount;
        this.variableSymbol = variableSymbol;
        this.constantSymbol = constantSymbol;
        this.specificSymbol = specificSymbol;
        this.dueDate = localDate.format(formatter);
        this.note = note;
    }

    public static Payment apply(String IBAN, String amount, String variableSymbol, String constantSymbol, String specificSymbol, LocalDate localDate, String note) {
        if(!IBANValidator.isValid(IBAN))
            throw new RuntimeException("Invalid IBAN " + IBAN);


        if(constantSymbol != null)
            validConstantSymbols.stream().filter(cs -> cs.equalsIgnoreCase(constantSymbol)).findAny().orElseThrow(() -> new RuntimeException("Invalid constant symbol " + constantSymbol));

        return new Payment(IBAN, amount, variableSymbol, constantSymbol, specificSymbol, localDate, note);
    }

    @Override
    public String toString() {
        return "Payment{" +
                "IBAN='" + IBAN + '\'' +
                ", amount='" + amount + '\'' +
                ", variableSymbol='" + variableSymbol + '\'' +
                ", constantSymbol='" + constantSymbol + '\'' +
                ", specificSymbol='" + specificSymbol + '\'' +
                ", dueDate='" + dueDate + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}