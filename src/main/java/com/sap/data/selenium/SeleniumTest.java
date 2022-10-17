package com.sap.data.selenium;

import com.sap.data.model.Payment;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.Scanner;

import static com.sap.data.Constants.*;

public class SeleniumTest {

    private WebDriver driver;

    public SeleniumTest(WebDriver webDriver) {
        this.driver = webDriver;
    }

    public void openWebPage(String url) {
        driver.get(url);
    }

    public void waitForInput() {
        final Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        System.out.println("Press ENTER to continue.");

    }


    public void addPaymentDetails(Payment payment) {
        final WebElement accountNumber = driver.findElement(By.xpath(ACCOUNT_NUMBER_XPATH));
        accountNumber.sendKeys(payment.IBAN);

        final WebElement amount = driver.findElement(By.xpath(AMOUNT_XPATH));
        amount.sendKeys(payment.amount);

//        WebElement dueDate = driver.findElement(By.xpath(DUE_DAT));
//        dueDate.sendKeys(payment.getDueDate());
//        dueDate.click();

        if(payment.variableSymbol != null) {
            final WebElement variableSymbol = driver.findElement(By.xpath(VARIABLE_SYMBOL_XPATH));
            variableSymbol.sendKeys(payment.variableSymbol);
        }

        if(payment.constantSymbol != null) {
            final WebElement constantSymbol = driver.findElement(By.xpath(CONSTANT_SYMBOL_XPATH));
            constantSymbol.sendKeys(payment.constantSymbol);
            constantSymbol.sendKeys(Keys.ENTER);
        }

        if(payment.specificSymbol != null) {
            final WebElement specificSymbol = driver.findElement(By.xpath(SPECIFIC_SYMBOL_XPATH));
            specificSymbol.sendKeys(payment.specificSymbol);
        }

        if(payment.note != null) {
            final WebElement note = driver.findElement(By.xpath(COMMENT_XPATH));
            note.sendKeys(payment.note);
        }

    }


    public boolean isErrorMessage() {
        return driver.findElements(By.cssSelector("div.errorbox ul li")).size() > 0;
    }


    public void clickAddPaymentButton() {
        driver.findElement(By.xpath(ADD_BTN_XPATH)).click();
        if(isErrorMessage()) {
            waitForInput();
        }
    }


    public void clickBackToListButton() {
        driver.findElement(By.xpath(LIST_BTN_XPATH)).click();
    }


    public void dummyClick() {
        driver.findElement(By.xpath(COMMENT_XPATH)).sendKeys("");
    }


}
