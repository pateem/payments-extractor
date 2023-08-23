package com.sap.data.model;

public class VubBulkPaymentRow {

    // can be 1 (payment) or 2
    public final String type;

    // due date in form dd.mm.yy
    public final String dueDate;

    public final String senderIban;

    public final String recieverIban;

    // in quotes
    public final String amount;

    public final String currency;

    // format "/VS123/SS123/KS123"
    public final String reference;
    // no special chars, diacritics - in quotes

    public final String comment;


    public VubBulkPaymentRow(String type, String date, String senderIban, String recieverIban, String amount, String currency, String reference, String comment) {
        this.type = type;
        dueDate = date;
        this.senderIban = senderIban;
        this.recieverIban = recieverIban;
        this.amount = amount;
        this.currency = currency;
        this.reference = reference;
        this.comment = comment;
    }
}
