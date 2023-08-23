package com.sap.data.mapper;

import com.sap.data.model.Payment;
import com.sap.data.model.VubBulkPaymentRow;
import org.apache.commons.lang3.StringUtils;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class VubBulkPaymentMapper {

    public static VubBulkPaymentRow map(String senderIban, Payment payment) {
        return new VubBulkPaymentRow(
                "1",
                LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yy")),
                senderIban,
                payment.IBAN,
                surroundWithQuotes(payment.amount.replace(".", ",")),
                "EUR",
                surroundWithQuotes(createReference(payment)),
                surroundWithQuotes(StringUtils.stripAccents(payment.note))
        );
    }

    private static String surroundWithQuotes(String s) {
        s = s == null ? "" : s;
        return "\"" + s + "\"";
    }

    private static String createReference(Payment payment) {
        StringBuilder referenceBuilder = new StringBuilder();
        if (StringUtils.isNotBlank(payment.variableSymbol)) {
            referenceBuilder.append("/VS").append(payment.variableSymbol);
        }
        if (StringUtils.isNotBlank(payment.specificSymbol)) {
            referenceBuilder.append("/SS").append(payment.specificSymbol);
        }
        if (StringUtils.isNotBlank(payment.constantSymbol)) {
            referenceBuilder.append("/KS").append(payment.constantSymbol);
        }
        return referenceBuilder.toString();
    }

}
