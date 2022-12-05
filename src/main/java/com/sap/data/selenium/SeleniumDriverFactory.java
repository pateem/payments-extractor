package com.sap.data.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class SeleniumDriverFactory {


    public WebDriver getDriver() {
        String osName = System.getProperty("os.name").toLowerCase();
        System.out.println(osName);
        String driverFileName = "";
        if (osName.contains("mac")) {
            driverFileName = "geckodriver";
        } else if (osName.contains("windows")) {
            driverFileName = "geckodriver.exe";
        } else if (osName.contains("linux")) {
            driverFileName = "geckodriver-linux";
        } else {
            throw new IllegalStateException("Unsupporeted operating system: " + osName);
        }

        URL firefoxURL = getClass().getClassLoader().getResource("driver" + File.separator + driverFileName);
        try {
            System.setProperty("webdriver.gecko.driver", Paths.get(firefoxURL.toURI()).toFile().getAbsolutePath());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        WebDriver driver = new FirefoxDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

}
