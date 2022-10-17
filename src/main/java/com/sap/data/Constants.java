package com.sap.data;

import java.util.regex.Pattern;

public class Constants {

    public static final String VUB_IB_URL = "https://ib.vub.sk/";

    public static final String AMOUNT_GROUP = "amount";
    public static final String VARIABLE_SYMBOL_GROUP = "vs";
    public static final String CONSTANT_SYMBOL_GROUP = "cs";
    public static final String SPECIFIC_SYMBOL_GROUP = "ss";
    public static final String NOTE_GROUP = "note";


    public static final String AMOUNT_REGEX = String.format("(?<%s>\\d+(?:\\s\\d+)?(?:,\\d+|\\.\\d+)?)", AMOUNT_GROUP);
    public static final String VARIABLE_SYMBOL_REGEX = String.format("(?:\\s+(?<%s>\\d{1,10}))", VARIABLE_SYMBOL_GROUP);
    public static final String CONSTANT_SYMBOL_REGEX = String.format("(?:\\s+(?<%s>\\d{4}))?", CONSTANT_SYMBOL_GROUP);
    public static final String SPECIFIC_SYMBOL_REGEX = String.format("(?:\\s+(?<%s>\\d{1,10}))?", SPECIFIC_SYMBOL_GROUP);
    public static final String NOTE_REGEX = String.format("(?:\\s+(?<%s>\\w+[\\w\\W]*))?", NOTE_GROUP);
    public static final Pattern PAYMENT_DETAILS_PATTERN = Pattern.compile("^" + AMOUNT_REGEX + VARIABLE_SYMBOL_REGEX + CONSTANT_SYMBOL_REGEX + SPECIFIC_SYMBOL_REGEX + NOTE_REGEX + "$");


    public static final String ACCOUNT_NUMBER_XPATH = "//label[contains(div, 'jemcu')]/div/div/input";
    public static final String AMOUNT_XPATH = "//label[contains(div, 'Suma')]/div/div/input";
    public static final String VARIABLE_SYMBOL_XPATH = "//label[contains(div, 'Variabil')]/div/div/input";
    public static final String SPECIFIC_SYMBOL_XPATH = "//label[contains(div, 'pecifick')]/div/div/input";
    public static final String CONSTANT_SYMBOL_XPATH = "//label[contains(div, 'Kon')]/div/div/div/input";
    public static final String ADD_BTN_XPATH = "//button[contains(span, 'a prida')]";
    public static final String LIST_BTN_XPATH = "//button[contains(span, 'na zoznam')]";
    public static final String COMMENT_XPATH = "//div/textarea";


}