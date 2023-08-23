package com.sap.data;

import com.sap.data.mapper.VubBulkPaymentMapper;
import com.sap.data.model.Payment;
import com.sap.data.model.VubBulkPaymentRow;
import com.sap.data.selenium.SeleniumDriverFactory;
import com.sap.data.selenium.SeleniumTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.Stream;

import static com.sap.data.Constants.*;

public class App {

    private static Logger logger = LogManager.getLogger(App.class);

    public static void main(String... args) throws URISyntaxException, IOException {

        Path path = Paths.get(App.class.getClassLoader().getResource("payment_details.txt").toURI());
        Stream<String> lines = Files.lines(path);

        final List<Payment> payments = new ArrayList<>();

        lines.forEach(line -> {
            line = line.replaceAll("\\s+", " ").trim();
            if (line.length() > 25) {
                payments.add(extractPaymentDetails(line));
            }
        });

        lines.close();

        String filename = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".txt";

        payments.forEach(payment -> {
            try (Writer output = new BufferedWriter(
                    new FileWriter(filename, true))) {
                VubBulkPaymentRow row = VubBulkPaymentMapper.map("SK0802000000000013424312", payment);
                StringBuilder line = new StringBuilder(row.type).append(",");
                line.append(row.dueDate).append(",");
                line.append(row.senderIban).append(",");
                line.append(row.recieverIban).append(",");
                line.append(row.amount).append(",");
                line.append(row.currency).append(",");
                line.append(row.reference).append(",");
                line.append(row.comment).append(System.lineSeparator());
                output.write(line.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        System.exit(0);

        final SeleniumDriverFactory seleniumDriverFactory = new SeleniumDriverFactory();
        SeleniumTest seleniumTest = new SeleniumTest(seleniumDriverFactory.getDriver());
        seleniumTest.openWebPage(VUB_IB_URL);
        seleniumTest.waitForInput();

        for (Payment payment : payments) {
            logger.info(payment);
            seleniumTest.addPaymentDetails(payment);
            seleniumTest.dummyClick();
            //seleniumTest.clickAddPaymentButton();
            seleniumTest.waitForInput();
        }

        seleniumTest.clickBackToListButton();

    }


    public static Payment extractPaymentDetails(String line) {
        int index = 25;
        String IBAN = line.substring(0, index).replaceAll("\\s+", "");

        while (IBAN.length() < 24) {
            IBAN = (IBAN + line.charAt(index++)).trim();
        }

        final String paymentDetailsLine = line.substring(index).trim();
        final Matcher matcher = PAYMENT_DETAILS_PATTERN.matcher(paymentDetailsLine);

        if (matcher.matches()) {

            final Payment payment = Payment.apply(IBAN,
                    matcher.group(AMOUNT_GROUP),
                    matcher.group(VARIABLE_SYMBOL_GROUP),
                    matcher.group(CONSTANT_SYMBOL_GROUP),
                    matcher.group(SPECIFIC_SYMBOL_GROUP),
                    LocalDate.now(),
                    matcher.group(NOTE_GROUP));


            System.out.println(payment);
            return payment;
        } else {
            throw new RuntimeException("No match for line:\n " + paymentDetailsLine);
        }
    }
}
